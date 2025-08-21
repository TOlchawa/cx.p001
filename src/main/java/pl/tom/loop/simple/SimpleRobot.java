package pl.tom.loop.simple;

import pl.tom.loop.core.*;

import static java.lang.Math.*;

/**
 * Reference implementation of Robot.process according to the specification.
 * - Computes linear time/energy compute costs from network size
 * - Applies continuous power drain each tick (idle + self-discharge)
 * - Updates short memory [x,y,heading] from previous drive output [dx,dy]
 * - Returns control command [vx, vy, dt]
 */
public class SimpleRobot extends Robot {
    private final NeuralNetSpec net;
    private final NeuralCostCoeffs coeffs;
    private final PowerModel power;

    // Tunables
    private final double perElemCost = 0.0005;           // I/O energy per float element
    private final double motionPowerPerSpeed = 0.02;     // optional motion energy cost
    private final boolean motionEnergyEnabled = false;   // can be enabled if desired

    // Battery degradation coefficients
    private final double kTime = 1e-5;   // per second
    private final double kLayers = 1e-6; // per layer
    private final double kNeurons = 5e-9;// per neuron

    public SimpleRobot(Sensor sensor, Actuator actuator, Drive drive, Battery battery,
                       NeuralNetSpec net, NeuralCostCoeffs coeffs, PowerModel power) {
        super(sensor, actuator, drive, battery, 3);
        this.net = net;
        this.coeffs = coeffs;
        this.power = power;
    }

    @Override
    public float[] process(float[] in) {
        // 1) Compute-time and compute-energy
        double T = coeffs.tBase() + coeffs.tPerLayer() * net.layers();
        double Ecompute = coeffs.eBase() + coeffs.ePerNeuron() * net.neurons();

        // 2) Tick duration
        double tickSec = max(coeffs.tickBudget(), T);
        double dtMove = max(0, coeffs.tickBudget() - T);

        // 3) Continuous power drain
        double Eidle = power.idleRatePerSecond() * tickSec;
        double Eleak = power.selfDischargePerSecond() * tickSec;
        double Ebase = Eidle + Eleak + Ecompute;
        Battery bat = battery();
        if (!bat.hasEnergy(Ebase)) {
            // Not enough energy: consume what we can from leak, degrade slightly, return no-op
            bat.consume(min(bat.currentLevel(), Eleak));
            degradeBattery(tickSec);
            return new float[]{0f, 0f, 0f};
        }
        bat.consume(Ebase);

        // 4) Unpack frame and I/O cost for inputs
        Frame f = FrameCodec.unpack(in);
        float[] s = f.getSensor();
        float[] d = f.getDrive();
        float[] a = f.getActuator();
        int ioIn = (s == null ? 0 : s.length) + (d == null ? 0 : d.length) + (a == null ? 0 : a.length);
        if (ioIn > 0) bat.consume(perElemCost * ioIn);

        // 5) Update memory from drive output [dx, dy]
        float[] mem = memory();
        double x = mem[0], y = mem[1], heading = mem[2];
        if (d != null && d.length >= 2) {
            double dx = d[0];
            double dy = d[1];
            x += dx; y += dy;
            if (abs(dx) > 1e-9 || abs(dy) > 1e-9) heading = atan2(dy, dx);
        }
        mem[0] = (float) x; mem[1] = (float) y; mem[2] = (float) heading;

        // 6) Control policy
        double sAvg = avg(s);
        double aAvg = avg(a);
        double dMag = mag2(d);
        double vx = clamp(0.6 * sAvg + 0.2 * aAvg + 0.2 * dMag, -1, 1);
        double vy = clamp(0.6 * sAvg - 0.2 * aAvg + 0.2 * dMag, -1, 1);

        // 7) Optional motion energy cost
        if (motionEnergyEnabled) {
            double Emove = motionPowerPerSpeed * hypot(vx, vy) * dtMove;
            bat.consume(Emove);
        }

        // 8) I/O cost for output command
        float[] cmd = new float[]{(float) vx, (float) vy, (float) dtMove};
        bat.consume(perElemCost * cmd.length);

        // 9) Degradation
        degradeBattery(tickSec);

        return cmd;
    }

    private void degradeBattery(double tickSec) {
        battery().degrade(kTime * tickSec + kLayers * net.layers() + kNeurons * net.neurons());
    }

    private static double avg(float[] v) {
        if (v == null || v.length == 0) return 0.0;
        double s = 0.0; for (float x : v) s += x; return s / v.length;
    }

    private static double mag2(float[] v) {
        if (v == null || v.length < 2) return 0.0;
        return hypot(v[0], v[1]);
    }

    private static double clamp(double v, double lo, double hi) {
        return max(lo, min(hi, v));
    }
}
