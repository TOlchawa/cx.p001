package pl.cx.p001.impl.simple;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Drive;

/**
 * Drive represents the robot's drive system, such as wheels or tracks.
 * It is characterized by its type and speed.
 */
@Data
@SuperBuilder
public class SimpleDrive extends Drive {
    private String id;
}
