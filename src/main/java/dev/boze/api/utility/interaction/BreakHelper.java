package dev.boze.api.utility.interaction;

import dev.boze.api.internal.Instances;
import dev.boze.api.utility.MathHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;

/**
 * BreakHelper provides utilities for breaking blocks with packets and rotations.
 * <br>
 * This is the breaking counterpart to {@link PlaceHelper}. It is intended for anarchy servers:
 * blocks are broken via packets, and rotations are handled through the {@link Interaction} system
 * so the break is anticheat-compliant when run from an interaction.
 */
public class BreakHelper {

    /**
     * Checks whether a block can be broken (reachable hardness, not air, etc.).
     *
     * @param pos The block position
     * @return true if the block can be broken
     */
    public static boolean canBreak(BlockPos pos) {
        return Instances.getInteraction().canBreak(pos);
    }

    /**
     * Finds the visible face to break, using the player's block interaction range and requiring
     * line of sight.
     *
     * @param pos The block position to break
     * @return The block hit result on the visible face, or null if the block can't be reached
     */
    public static BlockHitResult cast(BlockPos pos) {
        return cast(pos, Minecraft.getInstance().player.blockInteractionRange(), false);
    }

    /**
     * Finds the visible face to break.
     *
     * @param pos The block position to break
     * @param range The maximum reach range
     * @param throughWalls Whether to allow breaking without line of sight
     * @return The block hit result on the chosen face, or null if the block can't be reached
     */
    public static BlockHitResult cast(BlockPos pos, double range, boolean throughWalls) {
        return Instances.getInteraction().breakCast(pos, range, throughWalls);
    }

    /**
     * Breaks the block at the given position, using the player's block interaction range and
     * requiring line of sight. Swaps to the best tool automatically.
     *
     * @param pos The block position to break
     * @return true if a break was started
     */
    public static boolean breakBlock(BlockPos pos) {
        return breakBlock(pos, Minecraft.getInstance().player.blockInteractionRange(), false);
    }

    /**
     * Breaks the block at the given position. Swaps to the best tool automatically.
     *
     * @param pos The block position to break
     * @param range The maximum reach range
     * @param throughWalls Whether to allow breaking without line of sight
     * @return true if a break was started
     */
    public static boolean breakBlock(BlockPos pos, double range, boolean throughWalls) {
        return Instances.getInteraction().breakBlock(pos, range, throughWalls);
    }

    /**
     * Builds a ready-to-run break {@link Interaction} that rotates to the block's visible face and
     * then breaks it. Add the result to an {@link dev.boze.api.event.EventInteract} so rotations are
     * applied for the active anticheat mode.
     *
     * @param pos The block position to break
     * @param rotate Whether to rotate to the block before breaking
     * @return An interaction, or null if the block can't be reached
     */
    public static Interaction interaction(BlockPos pos, boolean rotate) {
        return interaction(pos, rotate, Minecraft.getInstance().player.blockInteractionRange(), false);
    }

    /**
     * Builds a ready-to-run break {@link Interaction} that rotates to the block's visible face and
     * then breaks it. Add the result to an {@link dev.boze.api.event.EventInteract} so rotations are
     * applied for the active anticheat mode.
     *
     * @param pos The block position to break
     * @param rotate Whether to rotate to the block before breaking
     * @param range The maximum reach range
     * @param throughWalls Whether to allow breaking without line of sight
     * @return An interaction, or null if the block can't be reached
     */
    public static Interaction interaction(BlockPos pos, boolean rotate, double range, boolean throughWalls) {
        BlockHitResult hitResult = cast(pos, range, throughWalls);
        if (hitResult == null) return null;

        float[] rotation = MathHelper.calculateRotation(hitResult.getLocation(), rotate);
        return new Interaction(() -> breakBlock(pos, range, throughWalls), rotate, rotation[0], rotation[1]);
    }
}
