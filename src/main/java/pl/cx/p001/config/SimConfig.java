package pl.cx.p001.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import pl.cx.p001.manager.ResourceManager;
import pl.cx.p001.manager.RobotsManager;
import pl.cx.p001.manager.SimulatorManager;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.ArenaProvider;
import pl.cx.p001.model.AssetType;
import pl.cx.p001.model.Cell;

import java.util.Random;

/**
 * Spring configuration for Arena initialization.
 * Provides beans for ArenaProvider and Arena with random assets.
 */
@Getter
@Configuration
public class SimConfig {
    // Arena size parameters
    @org.springframework.beans.factory.annotation.Value("${sim.arena.width:100}")
    private int arenaWidth;
    @org.springframework.beans.factory.annotation.Value("${sim.arena.height:100}")
    private int arenaHeight;
    @org.springframework.beans.factory.annotation.Value("${sim.arena.depth:1}")
    private int arenaDepth;


    @Bean
    public ArenaProvider arenaProvider(SimConfig simConfig) {
        return new ArenaProvider(simConfig);
    }

    @Bean
    public Arena arena(ArenaProvider provider) {
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
    ResourceManager getResourceManager(Arena arena) {
        return new ResourceManager(arena);
    }


    @Bean
    RobotsManager getRobotsManager(Arena arena) {
        return new RobotsManager(arena);
    }

    @Lazy
    SimulatorManager getSimulatorManager(ResourceManager resourceManager, RobotsManager robotsManager, Arena arena) {
        return new SimulatorManager(resourceManager, robotsManager, arena);
    }
}
