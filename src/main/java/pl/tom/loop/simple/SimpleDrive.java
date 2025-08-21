package pl.tom.loop.simple;

import pl.tom.loop.core.Drive;

/**
 * Reference drive that converts control command [vx, vy, dt] into displacement [dx, dy].
 * The output is limited by drive speed and assumed dt from the command.
 */
public class SimpleDrive extends Drive {
    public SimpleDrive(String type, double speed) {
        super(type, speed);
    }

    @Override
    public float[] process(float[] in) {
        if (in == null || in.length < 3) return new float[]{0f, 0f};
        double vx = clamp(in[0], -1f, 1f);
        double vy = clamp(in[1], -1f, 1f);
        double dt = Math.max(0, in[2]);
        double v = Math.hypot(vx, vy);
        if (v > 1e-6) {
            double scale = Math.min(1.0, 1.0 / v);
            vx *= scale; vy *= scale;
        }
        double dx = vx * speed() * dt;
        double dy = vy * speed() * dt;
        return new float[]{(float) dx, (float) dy};
    }

    private static float clamp(float v, float lo, float hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}

