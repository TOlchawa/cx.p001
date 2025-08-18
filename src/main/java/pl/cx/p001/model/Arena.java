package pl.cx.p001.model;

import pl.cx.p001.config.SimConfig;

/**
 * Arena represents the world map where gameplay takes place.
 * It is a rectangular grid of cells, each cell can hold assets/resources.
 */
public class Arena {
    private final int width;
    private final int height;
    private final int depth;
    private final Cell[][][] cells;

    public Arena(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.cells = new Cell[width][height][depth];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    cells[x][y][z] = new Cell();
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public Cell getCell(int x, int y, int z) {
        return cells[x][y][z];
    }

}
