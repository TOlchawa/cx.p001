package pl.cx.p001.impl.simple;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Sensor;

/**
 * Sensor represents a robot's sensor device, such as a camera or infrared sensor.
 * It is characterized by its type and detection range.
 */
@Data
@SuperBuilder
public class SimpleSensor extends Sensor {
    private String id;
}
