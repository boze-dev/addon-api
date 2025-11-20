package dev.boze.api.utility.interaction;

/**
 * Interaction represents a client to server interaction that may require rotation
 */
public class Interaction {
    private final Runnable action;
    private final boolean rotate;
    private final float yaw;
    private final float pitch;

    /**
     * Creates an interaction that doesn't require rotation
     *
     * @param action The action to run
     */
    public Interaction(Runnable action) {
        this.action = action;
        this.rotate = false;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    /**
     * Creates an interaction that requires specific rotation
     *
     * @param action The action to run
     * @param yaw The yaw rotation required
     * @param pitch The pitch rotation required
     */
    public Interaction(Runnable action, float yaw, float pitch) {
        this.action = action;
        this.rotate = true;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Creates an interaction that requires specific rotation
     *
     * @param action The action to run
     * @param rotate Whether to rotate or not
     * @param yaw The yaw rotation required
     * @param pitch The pitch rotation required
     */
    public Interaction(Runnable action, boolean rotate, float yaw, float pitch) {
        this.action = action;
        this.rotate = rotate;
        this.yaw = yaw;
        this.pitch = pitch;
    }


    /**
     * Returns whether this interaction requires rotation
     *
     * @return true if rotation is required, false otherwise
     */
    public boolean shouldRotate() {
        return rotate;
    }

    /**
     * Gets the yaw rotation
     *
     * @return The yaw rotation
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Gets the pitch rotation
     *
     * @return The pitch rotation
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * Runs the interaction action
     */
    public void run() {
        action.run();
    }
}
