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
import lombok.experimental.SuperBuilder;

/**
 * Actuator represents a robot manipulator, such as an arm or gripper.
 * It is characterized by its type and strength.
 */
@Data
@SuperBuilder
public class Actuator {
    private final String typeName;
    private final double strength;
}
