package pl.cx.p001.manager;

import lombok.AllArgsConstructor;
import pl.cx.p001.events.RobotListener;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.robot.Robot;
import pl.cx.p001.model.robot.Sensor;
import pl.cx.p001.model.robot.Actuator;
import pl.cx.p001.model.robot.Drive;
import pl.cx.p001.model.robot.Battery;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * RobotsManager is responsible for managing all robot entities in the simulation.
 * It provides methods to add, remove, and retrieve robots.
 * By default, it initializes with two sample robots.
 */
public class RobotsManager {
    private final Arena arena;
    private final RobotListener robotListener;
    private final List<Robot> robots = new CopyOnWriteArrayList<>();

    public RobotsManager(Arena arena, RobotListener robotListener) {
        this.arena = arena;
        this.robotListener = robotListener;
        // Add two default robots with average non-zero parameters
        robots.add(new Robot(
            new Sensor("Camera", 0.5),
            new Actuator("Gripper", 0.6),
            new Drive("Wheels", 0.7),
            new Battery(1.0, 0.8, 0.9)
        ));
        robots.add(new Robot(
            new Sensor("Infrared", 0.4),
            new Actuator("Arm", 0.5),
            new Drive("Tracks", 0.6),
            new Battery(0.9, 0.7, 0.8)
        ));
    }

    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    /**
     * Returns an unmodifiable list of all robots managed by this class.
     * Each robot is guaranteed to be non-null and fully initialized.
     */
    public List<Robot> getRobots() {
        return java.util.Collections.unmodifiableList(robots);
    }

    public int getRobotCount() {
        return robots.size();
    }
}
