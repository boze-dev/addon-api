package dev.boze.api.interaction;

import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

/**
 * Place Interaction
 *
 * Represents a place interaction with the server
 */
public class PlaceInteraction implements RotateInteraction {

    private final Runnable callback;

    private final Rotation rotation;

    public final BlockPos pos;
    public final Direction direction;

    public final Hand hand;
    public final boolean swing;

    public final int slot;

    /**
     * Creates a new place interaction
     *
     * @param callback The callback to run when the interaction is executed
     * @param rotation The rotation to rotate to, or null if no rotation is needed
     * @param pos The position to place the block on
     * @param direction The direction to place the block on
     * @param hand The hand to use
     * @param swing Whether to swing
     * @param slot The slot to use
     */
    public PlaceInteraction(Runnable callback, Rotation rotation, BlockPos pos, Direction direction, Hand hand, boolean swing, int slot) {
        this.callback = callback;
        this.rotation = rotation;
        this.pos = pos;
        this.direction = direction;
        this.hand = hand;
        this.swing = swing;
        this.slot = slot;
    }

    /**
     * Executes the interaction, then runs the callback - this is called by Boze, you shouldn't call this
     */
    @Override
    public void execute() {
        callback.run();
    }

    @Override
    public Rotation getRotation() {
        return rotation;
    }
}
