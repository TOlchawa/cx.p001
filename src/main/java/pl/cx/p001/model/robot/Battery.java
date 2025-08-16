/**
 * Battery represents the robot's power source.
 * It contains information about capacity, current charge, and condition.
 */
package pl.cx.p001.model.robot;

import lombok.Data;

@Data
public class Battery {
    /**
     * Maximum capacity of the battery (energy units)
     */
    private final double capacity;
    /**
     * Current charge level (energy units)
     */
    private double currentLevel;
    /**
     * Condition: 0.0 = destroyed, 1.0 = brand new
     */
    private double condition;

    public Battery(double capacity, double currentLevel, double condition) {
        this.capacity = capacity;
        this.currentLevel = currentLevel;
        this.condition = condition;
    }
}

