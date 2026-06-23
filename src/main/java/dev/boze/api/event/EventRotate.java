package dev.boze.api.event;

import dev.boze.api.utility.MathHelper;
import dev.boze.api.utility.interaction.InteractionMode;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;

/**
 * EventRotate
 * <br>
 * Event fired when the client is about to rotate the player.
 * This event allows addons to modify rotation values.
 * <br>
 * <strong>WARNING:</strong> If another feature already rotated, changing {@link #yaw} or {@link #pitch}
 * will likely break that feature.
 * <br>
 * To remain safe, only modify rotation values when {@link #isFree()} returns true.
 */
public class EventRotate extends CancellableEvent {
    private static final EventRotate INSTANCE = new EventRotate();

    /**
     * The yaw rotation value
     */
    public float yaw;

    /**
     * The pitch rotation value
     */
    public float pitch;

    /**
     * Whether the client is already rotating.
     * If true, modifying yaw/pitch can break client modules.
     */
    public boolean clientRotating;

    private InteractionMode mode;

    /**
     * Gets the event instance
     *
     * @param mode The interaction mode
     * @param yaw The yaw value
     * @param pitch The pitch value
     * @param clientRotating Whether the client is already rotating
     * @return The event instance
     */
    public static EventRotate get(InteractionMode mode, float yaw, float pitch, boolean clientRotating) {
        INSTANCE.mode = mode;
        INSTANCE.yaw = yaw;
        INSTANCE.pitch = pitch;
        INSTANCE.clientRotating = clientRotating;
        INSTANCE.setCancelled(false);
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
     * Returns whether this rotation event is free to modify.
     * A rotation event is free if it's not cancelled and the client is not already rotating.
     *
     * @return true if the rotation can be safely modified, false otherwise
     */
    public boolean isFree() {
        return !isCancelled() && !clientRotating;
    }

    /**
     * Calculates and sets the rotation to face the specified position from the given eye position.
     * This method should only be called when {@link #isFree()} returns true.
     *
     * @param eyes The eye position to calculate from
     * @param target The target position to rotate towards
     */
    public void rotate(Vec3 eyes, Vec3 target) {
        float[] rotation = MathHelper.calculateRotation(eyes, target);
        yaw = rotation[0];
        pitch = rotation[1];
    }

    /**
     * Calculates and sets the rotation to face the specified position.
     * This method should only be called when {@link #isFree()} returns true.
     *
     * @param target The target position to rotate towards
     */
    public void rotate(Vec3 target) {
        Minecraft mc = Minecraft.getInstance();
        Vec3 eyes = mc.player.getEyePosition();
        rotate(eyes, target);
    }
}
