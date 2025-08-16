package pl.cx.p001.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import pl.cx.p001.events.ArenaListener;
import pl.cx.p001.events.ArenaUpdateEvent;
import pl.cx.p001.events.RobotListener;
import pl.cx.p001.manager.ResourceManager;
import pl.cx.p001.manager.RobotsManager;
import pl.cx.p001.manager.SimulatorManager;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.ArenaProvider;
import pl.cx.p001.model.Cell;
import pl.cx.p001.model.AssetType;

import java.util.Random;

/**
 * Spring configuration for Arena initialization.
 * Provides beans for ArenaProvider and Arena with random assets.
 */
@Getter
@Configuration
public class SimConfig {
    // Arena size parameters
    private final int arenaWidth = 100;
    private final int arenaHeight = 100;
    private final int arenaDepth = 1;


    @Bean
    public ArenaProvider arenaProvider() {
        return new ArenaProvider();
    }

    @Bean
    public Arena arena(ArenaProvider provider, ArenaListener listener) {
        Arena arena = provider.generateArena(arenaWidth, arenaHeight, arenaDepth);
        Random rand = new Random();
        for (int x = 0; x < arena.getWidth(); x++) {
            for (int y = 0; y < arena.getHeight(); y++) {
                Cell cell = arena.getCell(x, y, 0);
                if (rand.nextDouble() < 0.05) cell.setAssetCount(AssetType.ENERGY, rand.nextInt(10) + 1);
                if (rand.nextDouble() < 0.05) cell.setAssetCount(AssetType.MATTER, rand.nextInt(10) + 1);
            }
        }
        return arena;
    }

    @Bean
    ResourceManager getResourceManager(Arena arena, ArenaListener listener) {
        return new ResourceManager(arena, listener);
    }


    @Bean
    RobotsManager getRobotsManager(Arena arena, RobotListener robotListener) {
        return new RobotsManager(arena, robotListener);
    }

    @Lazy
    SimulatorManager getSimulatorManager(ResourceManager resourceManager, RobotsManager robotsManager, Arena arena) {
        return new SimulatorManager(resourceManager, robotsManager, arena);
    }
}
