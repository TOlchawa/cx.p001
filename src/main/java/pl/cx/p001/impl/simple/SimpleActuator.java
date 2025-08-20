package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Actuator;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleActuator extends Actuator {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        return new float[0];
    }
}
