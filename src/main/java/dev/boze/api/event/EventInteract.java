package dev.boze.api.event;

import dev.boze.api.utility.interaction.Interaction;
import dev.boze.api.utility.interaction.InteractionMode;

import java.util.ArrayList;
import java.util.List;

/**
 * EventInteract
 * <br>
 * Event fired when the client is fetching interactions.
 * This event allows addons to add custom interactions that may require rotation.
 * <br>
 * Interactions added to this event will be executed by the client
 */
public class EventInteract {
    private static final EventInteract INSTANCE = new EventInteract();

    private InteractionMode mode;
    private final List<Interaction> interactions = new ArrayList<>();

    /**
     * Gets the event instance
     *
     * @param mode The interaction mode
     * @return The event instance
     */
    public static EventInteract get(InteractionMode mode) {
        INSTANCE.mode = mode;
        INSTANCE.interactions.clear();
        return INSTANCE;
    }

    /**
     * Gets the interaction mode
     *
     * @return The interaction mode
     */
    public InteractionMode getMode() {
        return mode;
    }

    /**
     * Adds an interaction to be executed
     *
     * @param interaction The interaction to add
     */
    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }

    /**
     * Gets all added interactions
     *
     * @return The list of interactions
     */
    public List<Interaction> getInteractions() {
        return interactions;
    }

    /**
     * Returns whether any of the added interactions require rotation
     *
     * @return true if any interaction requires rotation
     */
    public boolean hasRotatingInteractions() {
        return interactions.stream().anyMatch(Interaction::shouldRotate);
    }
}
