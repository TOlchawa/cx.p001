package pl.cx.p001.events;

/**
 * ArenaListener listens for events related to changes in the Arena.
 * Implement this interface to react to cell updates, asset changes, etc.
 */
public interface ArenaListener {
    /**
     * Called when an update event occurs in the Arena (e.g. cell or full map update).
     *
     * @param event the ArenaUpdateEvent containing update details
     */
    void onArenaUpdated(ArenaUpdateEvent event);
}
