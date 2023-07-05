package dev.boze.api.interaction;

/**
 * Interaction
 *
 * Represents an interaction with the server
 *
 * This can be used to rotate, place, or break blocks, interact with entities, etc.
 */
public interface Interaction {

    /**
     * Executes the interaction
     */
    void execute();
}
