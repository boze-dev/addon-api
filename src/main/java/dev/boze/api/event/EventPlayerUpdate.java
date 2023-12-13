package dev.boze.api.event;

import dev.boze.api.interaction.Interaction;

import java.util.LinkedList;

/**
 * EventPlayerUpdate
 * <p>
 * Event called when the player updates, once a tick
 * <p>
 * This event should be used for any interactions with the server
 * <p>
 * Interactions outside this event and EventGrim may not work properly, and may cause issues
 *
 * @see dev.boze.api.interaction.Interaction
 */
public class EventPlayerUpdate {
    private static final EventPlayerUpdate INSTANCE = new EventPlayerUpdate();

    private boolean bozeInteracting;

    public final LinkedList<Interaction> interactions = new LinkedList<>();

    /**
     * Addons that wish to have a lower priority than Boze should check this method
     * <p>
     * If this method returns true, the addon should not interact with the server
     *
     * @return Weather Boze itself is interacting with the server
     */
    public boolean isBozeInteracting() {
        return bozeInteracting;
    }

    /**
     * Gets the event instance - this is called once a tick by Boze, and should not be called by addons
     *
     * @param bozeInteracting Whether Boze is interacting with the server
     * @return The event instance
     */
    public static EventPlayerUpdate get(boolean bozeInteracting) {
        INSTANCE.bozeInteracting = bozeInteracting;
        INSTANCE.interactions.clear();
        return INSTANCE;
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
}
