package pl.cx.p001.model.robot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * Sensor represents a robot's sensor device, such as a camera or infrared sensor.
 * It is characterized by its type and detection range.
 * In other words: eyes, ears, nose, etc.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public abstract class Sensor {
    private final String type;
    private double range;
    public abstract float[] process(float[] in);
}
