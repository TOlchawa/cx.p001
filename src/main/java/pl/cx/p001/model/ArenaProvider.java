package pl.cx.p001.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.cx.p001.config.SimConfig;

/**
 * ArenaProvider is a service class for generating Arena instances.
 * Provides methods to create default or custom world maps.
 */
@Getter
@Setter
@AllArgsConstructor
public class ArenaProvider {
    private SimConfig simConfig;

    /**
     * Generates a default Arena with default size and assets.
     * All cells are initialized with zero MATTER and ENERGY.
     */
    public Arena generateDefaultArena() {
        Arena arena = new Arena(simConfig.getArenaWidth(), simConfig.getArenaHeight(), simConfig.getArenaDepth());
        for (int x = 0; x < arena.getWidth(); x++) {
            for (int y = 0; y < arena.getHeight(); y++) {
                for (int z = 0; z < arena.getDepth(); z++) {
                    Cell cell = arena.getCell(x, y, z);
                    cell.setAssetCount(AssetType.MATTER, 0);
                    cell.setAssetCount(AssetType.ENERGY, 0);
                }
            }
        }
        return arena;
    }

    /**
     * Generates an Arena with given size and initializes all cells with zero assets.
     */
    public Arena generateArena(int width, int height, int depth) {
        Arena arena = new Arena(width, height, depth);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    Cell cell = arena.getCell(x, y, z);
                    cell.setAssetCount(AssetType.MATTER, 0);
                    cell.setAssetCount(AssetType.ENERGY, 0);
                }
            }
        }
        return arena;
    }
}
