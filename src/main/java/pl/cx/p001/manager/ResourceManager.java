package pl.cx.p001.manager;

import lombok.AllArgsConstructor;
import pl.cx.p001.events.ArenaUpdateEvent;
import pl.cx.p001.gui.PixelGui;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.AssetType;
import pl.cx.p001.model.Cell;

/**
 * ResourceManager is responsible for managing assets (resources) on the Arena.
 * It provides methods to add, remove, and query asset quantities in specific cells.
 * Used for resource operations in the simulation.
 */
@AllArgsConstructor
public class ResourceManager {
    private final Arena arena;

    public void initialize() {
        PixelGui.getInstance().onArenaUpdated(new ArenaUpdateEvent(arena, 0, 0, 0, ArenaUpdateEvent.Type.FULL_UPDATE));
    }

    public void addAsset(int x, int y, int z, AssetType type, int amount) {
        Cell cell = arena.getCell(x, y, z);
        int current = cell.getAssetCount(type);
        cell.setAssetCount(type, current + amount);
        if (PixelGui.getInstance() != null) {
            PixelGui.getInstance().onArenaUpdated(new ArenaUpdateEvent(
                    arena, x, y, z, ArenaUpdateEvent.Type.CELL_UPDATE));
        }
    }

    public void removeAsset(int x, int y, int z, AssetType type, int amount) {
        Cell cell = arena.getCell(x, y, z);
        int current = cell.getAssetCount(type);
        cell.setAssetCount(type, Math.max(0, current - amount));
        if (PixelGui.getInstance() != null) {
            PixelGui.getInstance().onArenaUpdated(new ArenaUpdateEvent(
                    arena, x, y, z, ArenaUpdateEvent.Type.CELL_UPDATE));
        }
    }

    public int getAssetCount(int x, int y, int z, AssetType type) {
        return arena.getCell(x, y, z).getAssetCount(type);
    }
}
