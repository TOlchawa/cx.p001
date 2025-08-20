package pl.cx.p001.events;

/**
 * ArenaUpdateEvent represents an update event for the Arena.
 * Contains a reference to the Arena and the coordinates of the updated cell.
 */
public class ArenaUpdateEvent {
    private final pl.cx.p001.model.Arena arena;
    private final int x;
    private final int y;
    private final int z;
    private final Type type;

    public ArenaUpdateEvent(pl.cx.p001.model.Arena arena, int x, int y, int z, Type type) {
        this.arena = arena;
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
    }

    public pl.cx.p001.model.Arena getArena() {
        return arena;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        CELL_UPDATE,
        FULL_UPDATE
    }
}
