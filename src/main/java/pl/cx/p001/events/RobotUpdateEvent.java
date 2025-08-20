package pl.cx.p001.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.cx.p001.model.robot.Robot;

/**
 * RobotUpdateEvent represents an event describing a change in a robot's state.
 * Contains a reference to the robot and the type of change.
 */
@Getter
@RequiredArgsConstructor
public class RobotUpdateEvent {
    private final Robot robot;
    private final Type type;
    private final int x0, y0, z0;
    private final int x1, y1, z1;

    // For MOVE event
    public static RobotUpdateEvent move(Robot robot, int x0, int y0, int z0, int x1, int y1, int z1) {
        return new RobotUpdateEvent(robot, Type.MOVE, x0, y0, z0, x1, y1, z1);
    }

    // For CREATE/DESTROY event
    public static RobotUpdateEvent simple(Robot robot, Type type) {
        return new RobotUpdateEvent(robot, type, -1, -1, -1, -1, -1, -1);
    }

    public enum Type {
        MOVE,
        DESTROY,
        CREATE
    }
}
