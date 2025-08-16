package pl.cx.p001.model.robot;

import lombok.Data;

/**
 * Sensor represents a robot's sensor device, such as a camera or infrared sensor.
 * It is characterized by its type and detection range.
 */
@Data
public class Sensor {
    private final String type;
    private final double range;

    public Sensor(String type, double range) {
        this.type = type;
        this.range = range;
    }
}
