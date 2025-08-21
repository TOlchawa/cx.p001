package pl.cx.p001.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cx.p001.gui.PixelGui;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.robot.FrameCodec;
import pl.cx.p001.model.robot.Robot;

/**
 * SimulatorManager is responsible for managing the simulation logic and world generation.
 * Provides methods for creating and initializing the Arena (world map).
 */
@Service
@AllArgsConstructor
public class SimulatorManager {
    private final ResourceManager resourceManager;
    private final RobotsManager robotsManager;
    private final Arena arena;

    public void initialize() {
        Thread simulationThread = new Thread(this::runSimulation, "SimulationThread");
        simulationThread.setDaemon(true);
        simulationThread.start();
    }

    private void runSimulation() {
        // Ensure GUI is ready before first paint/event
        PixelGui.awaitReady();
        resourceManager.initialize();
        // Simulation loop example
        while (true) {
            for (Robot robot : robotsManager.getRobots()) {
                float[] frame = FrameCodec.pack(robot.getLastSensorOut(), robot.getLastDriveOut(), robot.getLastActuatorOut());
                float[] cmd = robot.process(frame);
                float[] sensorOut = robot.sensor().process(cmd);
                float[] driveOut = robot.drive().process(cmd);
                float[] actuatorOut = robot.actuator().process(cmd);
                robot.setLastSensorOut(sensorOut);
                robot.setLastDriveOut(driveOut);
                robot.setLastActuatorOut(actuatorOut);
            }
            try {
                Thread.sleep(500); // simulation tick
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
