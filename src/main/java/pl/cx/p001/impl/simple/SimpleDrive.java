package pl.cx.p001.impl.simple;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Drive;

import java.util.UUID;

/**
 * Drive represents the robot's drive system, such as wheels or tracks.
 * It is characterized by its type and speed.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleDrive extends Drive {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        return new float[0];
    }
}
