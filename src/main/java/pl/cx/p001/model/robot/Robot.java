package pl.cx.p001.model.robot;

import lombok.Data;

/**
 * Robot represents a complete robot entity in the simulation.
 * It consists of a sensor, actuator (manipulator), drive system, and battery.
 * Each component models a real-world robot part.
 */
@Data
public class Robot {
    private final Sensor sensor;
    private final Actuator actuator;
    private final Drive drive;
    private final Battery battery;

    public Robot(Sensor sensor, Actuator actuator, Drive drive, Battery battery) {
        this.sensor = sensor;
        this.actuator = actuator;
        this.drive = drive;
        this.battery = battery;
    }
}
