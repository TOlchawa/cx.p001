package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Robot;

import java.util.UUID;

/**
 * SimpleRobot is a basic implementation of the Robot class.
 * It serves as a simple example of a robot entity.
 * This class is immutable and can be created using the factory method createSimpleRobot().
 */
@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleRobot extends Robot {
    private final UUID id = UUID.randomUUID();

    @Override
    public float[] process(float[] in) {
        return new float[0];
    }
}
