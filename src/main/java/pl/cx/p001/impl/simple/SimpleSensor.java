package pl.cx.p001.impl.simple;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Sensor;

import java.util.UUID;

/**
 * Sensor represents a robot's sensor device, such as a camera or infrared sensor.
 * It is characterized by its type and detection range.
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleSensor extends Sensor {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        return new float[0];
    }
}
