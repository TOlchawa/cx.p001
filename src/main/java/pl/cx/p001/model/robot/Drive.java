package pl.cx.p001.model.robot;

import lombok.Data;

/**
 * Drive represents the robot's drive system, such as wheels or tracks.
 * It is characterized by its type and speed.
 */
@Data
public class Drive {
    private final String type;
    private final double speed;

    public Drive(String type, double speed) {
        this.type = type;
        this.speed = speed;
    }
}
