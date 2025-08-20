package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Actuator;

@Getter
@Setter
@SuperBuilder
public class SimpleActuator extends Actuator {
    private final String id;
}
