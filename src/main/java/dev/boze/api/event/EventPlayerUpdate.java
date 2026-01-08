package dev.boze.api.event;

/**
 * EventPlayerUpdate
 * <br>
 * Event called when the player updates, once a tick
 */
public class EventPlayerUpdate {
    private static final EventPlayerUpdate INSTANCE = new EventPlayerUpdate();

    public static EventPlayerUpdate get() {
        return INSTANCE;
    }
}
