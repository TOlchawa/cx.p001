package pl.tom.loop.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Actuator core abstraction. Processes control command and returns actuator output for next frame.
 */
@Getter
@Setter
@Accessors(fluent = true)
public abstract class Actuator {
    private final String type;
    private double powerLimit; // normalized 0..1

    protected Actuator(String type, double powerLimit) {
        this.type = type;
        this.powerLimit = powerLimit;
    }

    public abstract float[] process(float[] in);
}

