package pl.cx.p001.model.robot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Robot represents a complete robot entity in the simulation.
 * It acts as the "brain" of the robot, controlling its behavior and interactions.
 * It consists of a sensor, actuator (manipulator), drive system, and battery.
 * Each component models a real-world robot part.
 */
@Data
@SuperBuilder
public class Robot {
    private Sensor sensor;
    private Actuator actuator;
    private Drive drive;
    private Battery battery;
}
