package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Robot;

/**
 * SimpleRobot is a basic implementation of the Robot class.
 * It serves as a simple example of a robot entity.
 * This class is immutable and can be created using the factory method createSimpleRobot().
 */
@Getter
@Setter
@SuperBuilder
public class SimpleRobot extends Robot {

    private String id;

}
