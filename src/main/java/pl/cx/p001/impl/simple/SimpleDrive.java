package pl.cx.p001.impl.simple;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Drive;

import java.util.UUID;

/**
 * Drive converts control command [vx,vy,dt] into displacement [dx,dy] limited by speed.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleDrive extends Drive {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        if (in == null || in.length < 3) return new float[]{0f,0f};
        double vx = clamp(in[0]);
        double vy = clamp(in[1]);
        double dt = Math.max(0, in[2]);
        double mag = Math.hypot(vx, vy);
        if (mag > 1e-6 && mag > 1.0) { vx /= mag; vy /= mag; }
        double dx = vx * speed() * dt;
        double dy = vy * speed() * dt;
        return new float[]{(float)dx,(float)dy};
    }

    private double clamp(double v) { return Math.max(-1.0, Math.min(1.0, v)); }
}
