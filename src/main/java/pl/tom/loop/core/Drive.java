package pl.tom.loop.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Drive core abstraction. Processes control command and returns displacement [dx, dy].
 */
@Getter
@Setter
@Accessors(fluent = true)
public abstract class Drive {
    private final String type;
    private double speed; // units per second

    protected Drive(String type, double speed) {
        this.type = type;
        this.speed = speed;
    }

    public abstract float[] process(float[] in);
}

