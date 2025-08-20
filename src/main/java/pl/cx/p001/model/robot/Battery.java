/**
 * Battery represents the robot's power source.
 * It contains information about capacity, current charge, and condition.
 */
package pl.cx.p001.model.robot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Battery represents the robot's power source.
 * It contains information about capacity, current charge, and condition.
 * In other words: sugar, fat, fuel tank, battery, etc.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class Battery {
    /**
     * Maximum capacity of the battery (energy units)
     */
    private double capacity;
    /**
     * Current charge level (energy units)
     */
    private double currentLevel;
    /**
     * Condition: 0.0 = destroyed, 1.0 = brand new
     */
    private double condition;

}

