package pl.cx.p001.manager;

import pl.cx.p001.impl.simple.*;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.robot.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * RobotsManager is responsible for managing all robot entities in the simulation.
 * It provides methods to add, remove, and retrieve robots.
 * By default, it initializes with two sample robots.
 */
public class RobotsManager {
    private final Arena arena;

    private final List<Robot> robots = new CopyOnWriteArrayList<>();

    public RobotsManager(Arena arena) {
        this.arena = arena;
        // Add two default robots with average non-zero parameters
        robots.add(SimpleRobot.builder()
                .sensor(SimpleSensor.builder().type("Camera").range(0.5).build())
                .actuator(SimpleActuator.builder().typeName("Gripper").strength(0.6).build())
                .drive(SimpleDrive.builder().type("Wheels").speed(0.7).build())
                .battery(SimpleBattery.builder().capacity(1.0).condition(0.8).currentLevel(0.9).build())
                .build()
        );
        robots.add(SimpleRobot.builder()
                .sensor(SimpleSensor.builder().type("Infrared").range(0.4).build())
                .actuator(SimpleActuator.builder().typeName("Arm").strength(0.5).build())
                .drive(SimpleDrive.builder().type("Tracks").speed(0.6).build())
                .battery(SimpleBattery.builder().currentLevel(0.9).condition(0.7).capacity(0.8).build())
                .build()
        );
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
