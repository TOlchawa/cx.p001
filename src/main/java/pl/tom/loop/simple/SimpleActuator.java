package pl.tom.loop.simple;

import pl.tom.loop.core.Actuator;

/**
 * Reference actuator with simple saturation of command input.
 */
public class SimpleActuator extends Actuator {
    public SimpleActuator(String type, double powerLimit) {
        super(type, powerLimit);
    }

    @Override
    public float[] process(float[] in) {
        if (in == null) return new float[0];
        float[] out = new float[in.length];
        for (int i = 0; i < in.length; i++) {
            out[i] = (float) clamp(in[i] * powerLimit(), -1, 1);
        }
        return out;
    }

    private static double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}

