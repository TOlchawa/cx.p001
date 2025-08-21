package pl.cx.p001.impl.simple;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.*;

import java.util.UUID;

import static java.lang.Math.*;

/**
 * SimpleRobot: referencyjna implementacja logiki kroku zgodna ze specyfikacją.
 * Wejście: spakowane poprzednie wyjścia podsystemów (sensor, drive, actuator) via FrameCodec.
 * Wyjście: komenda sterująca [vx, vy, dt] dla następnego kroku.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleRobot extends Robot {
    private final UUID id = UUID.randomUUID();

    // Parametry "mózgu" i zasilania
    private final NeuralNetSpec netSpec;
    private final NeuralCostCoeffs costCoeffs;
    private final PowerModel powerModel;

    // Stałe / tunables
    @Builder.Default private final double perElemCost = 0.0005;      // energia na element I/O
    @Builder.Default private final double motionPowerPerSpeed = 0.02; // koszt ruchu (opcjonalnie)
    @Builder.Default private final boolean motionEnergyEnabled = false;
    // Degradacja baterii
    @Builder.Default private final double kTime = 1e-5;
    @Builder.Default private final double kLayers = 1e-6;
    @Builder.Default private final double kNeurons = 5e-9;

    @Override
    public float[] process(float[] in) {
        // 1. Koszty obliczeń
        double T = costCoeffs.getTBase() + costCoeffs.getTPerLayer() * netSpec.getLayers();
        double Ecompute = costCoeffs.getEBase() + costCoeffs.getEPerNeuron() * netSpec.getNeurons();

        // 2. Czas kroku
        double tickSec = max(costCoeffs.getTickBudget(), T);
        double dtMove = max(0, costCoeffs.getTickBudget() - T);

        // 3. Ciągły pobór energii
        Battery bat = battery();
        double Eidle = powerModel.getIdleRatePerSecond() * tickSec;
        double Eleak = powerModel.getSelfDischargePerSecond() * tickSec;
        double Ebase = Eidle + Eleak + Ecompute;
        if (!bat.hasEnergy(Ebase)) {
            // Minimalny pobór (leak) jeśli brak energii
            bat.consume(min(bat.currentLevel(), Eleak));
            degradeBattery(tickSec);
            return new float[]{0f,0f,0f};
        }
        bat.consume(Ebase);

        // 4. Rozpakowanie ramki
        Frame f = FrameCodec.unpack(in);
        float[] sPrev = f.getSensor();
        float[] dPrev = f.getDrive();
        float[] aPrev = f.getActuator();
        int ioIn = sPrev.length + dPrev.length + aPrev.length;
        if (ioIn > 0) bat.consume(perElemCost * ioIn);

        // 5. Aktualizacja pamięci z dPrev [dx,dy]
        float[] mem = memory();
        double x = mem[0], y = mem[1], heading = mem[2];
        if (dPrev.length >= 2) {
            double dx = dPrev[0];
            double dy = dPrev[1];
            x += dx; y += dy;
            if (abs(dx) > 1e-9 || abs(dy) > 1e-9) heading = atan2(dy, dx);
        }
        mem[0] = (float) x; mem[1] = (float) y; mem[2] = (float) heading;

        // 6. Polityka sterowania
        double sAvg = avg(sPrev);
        double aAvg = avg(aPrev);
        double dMag = hypot(dPrev.length>=2?dPrev[0]:0, dPrev.length>=2?dPrev[1]:0);
        double vx = clamp(0.6*sAvg + 0.2*aAvg + 0.2*dMag, -1, 1);
        double vy = clamp(0.6*sAvg - 0.2*aAvg + 0.2*dMag, -1, 1);

        // 7. Opcjonalny koszt ruchu
        if (motionEnergyEnabled) bat.consume(motionPowerPerSpeed * hypot(vx, vy) * dtMove);

        // 8. Komenda wynikowa
        float[] cmd = new float[]{(float)vx, (float)vy, (float)dtMove};
        bat.consume(perElemCost * cmd.length);
        degradeBattery(tickSec);
        return cmd;
    }

    private void degradeBattery(double tickSec) {
        battery().degrade(kTime * tickSec + kLayers * netSpec.getLayers() + kNeurons * netSpec.getNeurons());
    }

    private static double avg(float[] v) { if (v==null || v.length==0) return 0; double s=0; for(float x: v)s+=x; return s/v.length; }
    private static double clamp(double v,double lo,double hi){ return max(lo,min(hi,v)); }
}
