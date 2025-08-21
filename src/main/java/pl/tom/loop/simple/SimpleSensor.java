package pl.tom.loop.simple;

import pl.tom.loop.core.Sensor;

/**
 * Reference sensor that emits a small deterministic signal based on command input.
 */
public class SimpleSensor extends Sensor {
    public SimpleSensor(String type, double range) {
        super(type, range);
    }

    @Override
    public float[] process(float[] in) {
        // Return a tiny 2-element signal: scaled sums of input
        if (in == null || in.length == 0) return new float[]{0f, 0f};
        float sum = 0f;
        for (float v : in) sum += v;
        float a = (float) Math.tanh(sum * 0.01);
        float b = (float) Math.tanh(sum * 0.02);
        return new float[]{a, b};
    }
}

