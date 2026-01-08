package dev.boze.api.utility.interaction;

/**
 * Interaction mode for rotation and interaction
 * <br>
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
