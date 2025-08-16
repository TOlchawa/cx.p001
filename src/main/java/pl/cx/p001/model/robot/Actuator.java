/**
 * Package pl.cx.p001.model.robot contains classes representing robot components:
 * - Robot: main robot entity
 * - Sensor: robot sensors
 * - Actuator: robot manipulators (e.g. arms, grippers)
 * - Drive: robot drive systems (e.g. wheels, tracks)
 * - Battery: robot power source
 */
package pl.cx.p001.model.robot;

import lombok.Data;

/**
 * Actuator represents a robot manipulator, such as an arm or gripper.
 * It is characterized by its type and strength.
 */
@Data
public class Actuator {
    private final String type;
    private final double strength;

    public Actuator(String type, double strength) {
        this.type = type;
        this.strength = strength;
    }
}
