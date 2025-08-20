package pl.cx.p001.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cx.p001.model.Arena;

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
        Thread simulationThread = new Thread(() -> runSimulation(), "SimulationThread");
        simulationThread.setDaemon(true);
        simulationThread.start();
    }

    private void runSimulation() {
        try {
            Thread.sleep(1000);
            resourceManager.initialize();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Simulation loop example
        while (true) {
            robotsManager.getRobots().forEach(robot -> {
                // sensor OUT -->  IN think OUT -->
                //                      | [IN sensor OUT     --> battery] --> ...
                //                      | [IN actuator       --> battery]
                //                      | [IN drive          --> battery]
                float[] commands = robot.process(robot.sensor().process(robot.memory()));
                robot.actuator().process(commands);
                robot.drive().process(commands);
                // to jest do rozwazenia - powinno byc rownolegle
            });
            try {
                Thread.sleep(1000); // simulation tick
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
