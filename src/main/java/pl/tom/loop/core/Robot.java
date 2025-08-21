package pl.tom.loop.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Robot core abstraction. Holds Sensor, Drive, Actuator, Battery and a small state memory [x,y,heading].
 * The process() method accepts a packed frame and returns a control command [vx, vy, dt].
 */
@Getter
@Setter
@Accessors(fluent = true)
public abstract class Robot {
    private Sensor sensor;
    private Actuator actuator;
    private Drive drive;
    private Battery battery;
    // memory[0] = x, [1] = y, [2] = heading (radians)
    private float[] memory;

    protected Robot(Sensor sensor, Actuator actuator, Drive drive, Battery battery, int memorySize) {
        this.sensor = sensor;
        this.actuator = actuator;
        this.drive = drive;
        this.battery = battery;
        this.memory = new float[Math.max(3, memorySize)];
    }

    public abstract float[] process(float[] in);
}

