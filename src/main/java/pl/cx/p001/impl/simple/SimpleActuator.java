package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Actuator;

import java.util.UUID;

/**
 * Prosta implementacja Actuator: skaluje wejście przez strength i ogranicza do [-1,1].
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleActuator extends Actuator {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        if (in == null) return new float[0];
        float[] out = new float[in.length];
        for (int i=0;i<in.length;i++) {
            double v = in[i] * strength();
            if (v > 1) v = 1; else if (v < -1) v = -1;
            out[i] = (float) v;
        }
        return out;
    }
}
