package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Sensor;

import java.util.UUID;

/**
 * SimpleSensor generuje niewielki deterministyczny sygnał na podstawie komendy sterującej.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleSensor extends Sensor {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        if (in == null || in.length == 0) return new float[]{0f,0f};
        float sum = 0f; for (float v: in) sum += v;
        float a = (float) Math.tanh(sum * 0.01);
        float b = (float) Math.tanh(sum * 0.02);
        return new float[]{a,b};
    }
}
