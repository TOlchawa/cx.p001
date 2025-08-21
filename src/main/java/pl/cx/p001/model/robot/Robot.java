package pl.cx.p001.model.robot;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Robot represents a complete robot entity in the simulation.
 * It acts as the "brain" of the robot, controlling its behavior and interactions.
 * It consists of a sensor, actuator (manipulator), drive system, and battery.
 * Each component models a real-world robot part.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public abstract class Robot {
    private Sensor sensor;
    private Actuator actuator;
    private Drive drive;
    private Battery battery;
    @Builder.Default
    private float[] memory = new float[3]; // [x,y,heading]
    @Builder.Default
    private float[] lastSensorOut = new float[0];
    @Builder.Default
    private float[] lastDriveOut = new float[0];
    @Builder.Default
    private float[] lastActuatorOut = new float[0];

    // Explicit classic getters/setters for compatibility with non-fluent code
    public float[] getLastSensorOut() { return lastSensorOut; }
    public void setLastSensorOut(float[] v) { this.lastSensorOut = (v == null ? new float[0] : v); }
    public float[] getLastDriveOut() { return lastDriveOut; }
    public void setLastDriveOut(float[] v) { this.lastDriveOut = (v == null ? new float[0] : v); }
    public float[] getLastActuatorOut() { return lastActuatorOut; }
    public void setLastActuatorOut(float[] v) { this.lastActuatorOut = (v == null ? new float[0] : v); }

    public abstract float[] process(float[] in);
}
