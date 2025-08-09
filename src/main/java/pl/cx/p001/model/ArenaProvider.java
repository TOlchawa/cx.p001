package pl.cx.p001.model;

public class ArenaProvider {
    /**
     * Generates a default Arena with default size and assets.
     * All cells are initialized with zero MATTER and ENERGY.
     */
    public Arena generateDefaultArena() {
        Arena arena = new Arena();
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
}

