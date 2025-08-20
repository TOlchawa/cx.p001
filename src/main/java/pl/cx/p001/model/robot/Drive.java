package pl.cx.p001.model.robot;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Drive represents the robot's drive system, such as wheels or tracks.
 * It is characterized by its type and speed.
 * In other words: legs, wheels, tracks, etc.
 */
@Data
@SuperBuilder
@Accessors(fluent = true)
public abstract class Drive {
    private final String type;
    private double speed;
    public abstract float[] process(float[] in);
}
