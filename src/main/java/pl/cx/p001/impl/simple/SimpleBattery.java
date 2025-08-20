package pl.cx.p001.impl.simple;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import pl.cx.p001.model.robot.Battery;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@Accessors(fluent = true)
public class SimpleBattery extends Battery {
    private final UUID id = UUID.randomUUID();
}
