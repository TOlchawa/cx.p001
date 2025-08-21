package pl.tom.loop.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Sensor core abstraction. Processes control command and returns sensor output for the next frame.
 */
@Getter
@Setter
@Accessors(fluent = true)
public abstract class Sensor {
    private final String type;
    private double range;

    protected Sensor(String type, double range) {
        this.type = type;
        this.range = range;
    }

    public abstract float[] process(float[] in);
}

