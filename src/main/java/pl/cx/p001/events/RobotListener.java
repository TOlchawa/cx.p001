package pl.cx.p001.events;

/**
 * RobotListener listens for events related to robot state changes.
 * Implement this interface to react to robot moves, creation, or destruction.
 */
public interface RobotListener {
    void onRobotUpdated(RobotUpdateEvent event);
}

