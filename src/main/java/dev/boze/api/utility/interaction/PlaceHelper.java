package dev.boze.api.utility.interaction;

import dev.boze.api.internal.Instances;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

/**
 * PlaceHelper provides utilities for casting block placement interactions
 */
public class PlaceHelper {

    /**
     * Cast a block placement interaction at the specified position
     *
     * @param pos The position to cast at
     * @param mode The interaction mode to use
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, InteractionMode mode) {
        return Instances.getRayCasting().cast(pos, mode, 4.5, 0.0, true, false);
    }

    /**
     * Cast a block placement interaction at the specified position with custom range
     * <p>
     * Note: range/wallsRange won't apply if interactionMode is GRIM
     *
     * @param pos The position to cast at
     * @param mode The interaction mode to use
     * @param range The reach range to use
     * @param wallsRange The walls reach range to use (only applies if mode is NCP)
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, InteractionMode mode, double range, double wallsRange) {
        return Instances.getRayCasting().cast(pos, mode, range, wallsRange, true, false);
    }

    /**
     * Cast a block placement interaction at the specified position with strict direction setting
     *
     * @param pos The position to cast at
     * @param mode The interaction mode to use
     * @param strictDirection Whether to use strict direction checking
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, InteractionMode mode, boolean strictDirection) {
        return Instances.getRayCasting().cast(pos, mode, 4.5, 0.0, strictDirection, false);
    }

    /**
     * Cast a block placement interaction at the specified position with custom range and strict direction
     * <p>
     * Note: range/wallsRange won't apply if interactionMode is GRIM
     *
     * @param pos The position to cast at
     * @param mode The interaction mode to use
     * @param range The reach range to use
     * @param wallsRange The walls reach range to use (only applies if mode is NCP)
     * @param strictDirection Whether to use strict direction checking
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, InteractionMode mode, double range, double wallsRange, boolean strictDirection) {
        return Instances.getRayCasting().cast(pos, mode, range, wallsRange, strictDirection, false);
    }

    /**
     * Cast a block placement interaction at the specified position with air place support
     *
     * @param pos The position to cast at
     * @param airPlace Whether to allow placement against air blocks
     * @param mode The interaction mode to use
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, boolean airPlace, InteractionMode mode) {
        return Instances.getRayCasting().cast(pos, mode, 4.5, 0.0, true, airPlace);
    }

    /**
     * Cast a block placement interaction at the specified position with custom range and air place support
     * <p>
     * Note: range/wallsRange won't apply if interactionMode is GRIM
     *
     * @param pos The position to cast at
     * @param airPlace Whether to allow placement against air blocks
     * @param mode The interaction mode to use
     * @param range The reach range to use
     * @param wallsRange The walls reach range to use (only applies if mode is NCP)
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, boolean airPlace, InteractionMode mode, double range, double wallsRange) {
        return Instances.getRayCasting().cast(pos, mode, range, wallsRange, true, airPlace);
    }

    /**
     * Cast a block placement interaction at the specified position with strict direction and air place support
     *
     * @param pos The position to cast at
     * @param airPlace Whether to allow placement against air blocks
     * @param mode The interaction mode to use
     * @param strictDirection Whether to use strict direction checking
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, boolean airPlace, InteractionMode mode, boolean strictDirection) {
        return Instances.getRayCasting().cast(pos, mode, 4.5, 0.0, strictDirection, airPlace);
    }

    /**
     * Cast a block placement interaction at the specified position with custom range, strict direction, and air place support
     * <p>
     * Note: range/wallsRange won't apply if interactionMode is GRIM
     *
     * @param pos The position to cast at
     * @param airPlace Whether to allow placement against air blocks
     * @param mode The interaction mode to use
     * @param range The reach range to use
     * @param wallsRange The walls reach range to use (only applies if mode is NCP)
     * @param strictDirection Whether to use strict direction checking
     * @return The block hit result, or null if no valid placement found
     */
    public static BlockHitResult cast(BlockPos pos, boolean airPlace, InteractionMode mode, double range, double wallsRange, boolean strictDirection) {
        return Instances.getRayCasting().cast(pos, mode, range, wallsRange, strictDirection, airPlace);
    }

    /**
     * Place a block at the specified hit result location
     *
     * @param mode The interaction mode to use
     * @param hitResult The block hit result from a cast operation
     * @param hand The hand to use for placement
     * @return true if placement was successful
     */
    public static boolean place(InteractionMode mode, BlockHitResult hitResult, InteractionHand hand) {
        return Instances.getInteraction().place(mode, hitResult, hand);
    }

    /**
     * Check if a position is free from non-replaceable blocks, entities, and pending placements
     * <br>
     * It's recommended to check this before casting
     *
     * @param pos The block pos to check
     * @return true if pos is empty
     */
    public static boolean isEmpty(BlockPos pos) {
        return Instances.getInteraction().isEmpty(pos);
    }
}
