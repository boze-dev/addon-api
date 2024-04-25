package dev.boze.api.interaction;

/**
 * Rotation
 * <p>
 * Represents a rotation
 */
public class Rotation {

    public Rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Yaw to rotate to
     */
    public final float yaw;

    /**
     * Pitch to rotate to
     */
    public final float pitch;
}
