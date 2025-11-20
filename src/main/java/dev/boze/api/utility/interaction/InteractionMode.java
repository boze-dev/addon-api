package dev.boze.api.utility.interaction;

/**
 * Interaction mode for rotation and interaction
 * <p></p>
 * Determines which anti-cheat handler to use for handling rotation/interacting
 */
public enum InteractionMode {
    /**
     * NCP-compatible rotation mode
     */
    NCP,

    /**
     * GrimAC-compatible rotation mode
     */
    Grim
}
