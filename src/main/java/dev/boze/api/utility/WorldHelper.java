package dev.boze.api.utility;

import dev.boze.api.internal.Instances;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * WorldHelper provides world and block-related utilities.
 * <p>
 * This class offers comprehensive utilities for interacting with the Minecraft world,
 * including hole detection, block state queries, placement validation, and mining operations.
 * All methods handle null positions and world safety appropriately.
 * </p>
 */
public class WorldHelper {

    // Hole detection

    /**
     * Checks if a position is a hole.
     * <p>
     * A hole is surrounded by solid blocks on all horizontal sides and below,
     * with air blocks at the hole position and above it.
     * </p>
     *
     *
     * @param pos The position to check
     * @return true if the position is a hole
     */
    public static boolean isHole(BlockPos pos) {
        return Instances.getWorld().isHole(pos, false);
    }

    /**
     * Checks if a position is a hole, with support for double holes.
     *
     * @param pos The position to check
     * @param doubles Whether to include checks for 2x1 double holes
     * @return true if the position is a hole
     */
    public static boolean isHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isHole(pos, doubles);
    }

    /**
     * Checks if a position is a safe hole.
     * <p>
     * A safe hole is surrounded by <b>unbreakable blocks</b> (e.g., Bedrock, End Portal Frames)
     * on all relevant sides. This provides maximum protection from crystal damage as the
     * walls cannot be broken or exploded.
     * </p>
     *
     *
     * @param pos The position to check
     * @return true if the position is a safe hole
     */
    public static boolean isSafeHole(BlockPos pos) {
        return Instances.getWorld().isSafeHole(pos, false);
    }

    /**
     * Checks if a position is a safe hole, with support for double holes.
     * <p>
     * A safe hole is surrounded by <b>unbreakable blocks</b> on all sides.
     * </p>
     *
     * @param pos The position to check
     * @param doubles Whether to include checks for 2x1 double holes
     * @return true if the position is a safe hole
     */
    public static boolean isSafeHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isSafeHole(pos, doubles);
    }

    /**
     * Checks if a position is an unsafe hole.
     * <p>
     * An unsafe hole is surrounded by blast-resistant blocks (like Obsidian) that can eventually be broken.
     * This provides blast protection but is less secure than a safe hole.
     * </p>
     *
     * @param pos The position to check
     * @return true if the position is an unsafe hole
     */
    public static boolean isUnsafeHole(BlockPos pos) {
        return Instances.getWorld().isUnsafeHole(pos, false);
    }

    /**
     * Checks if a position is an unsafe hole, with support for double holes.
     * <p>
     * An unsafe hole is surrounded by blast-resistant blocks (like Obsidian).
     * </p>
     *
     * @param pos The position to check
     * @param doubles Whether to include checks for 2x1 double holes
     * @return true if the position is an unsafe hole
     */
    public static boolean isUnsafeHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isUnsafeHole(pos, doubles);
    }

    /**
     * Finds a connected double hole adjacent to the given position.
     * <p>
     * Searches west, east, north, and south positions for a connected hole
     * that forms a double hole pattern with the given position.
     * </p>
     *
     *
     * @param pos The reference position
     * @return The position of the connected hole, or null if none found
     */
    public static BlockPos findDoubleHole(BlockPos pos) {
        return Instances.getWorld().findDoubleHole(pos);
    }

    /**
     * Finds a connected safe double hole adjacent to the given position.
     * <p>
     * Requires the entire perimeter to be composed of <b>unbreakable blocks</b>.
     * </p>
     *
     * @param pos The reference position
     * @return The position of the connected safe hole, or null if none found
     */
    public static BlockPos findSafeDoubleHole(BlockPos pos) {
        return Instances.getWorld().findSafeDoubleHole(pos);
    }

    /**
     * Finds a connected unsafe double hole adjacent to the given position.
     * <p>
     * Requires the entire perimeter to be composed of blast-resistant blocks.
     * </p>
     *
     * @param pos The reference position
     * @return The position of the connected unsafe hole, or null if none found
     */
    public static BlockPos findUnsafeDoubleHole(BlockPos pos) {
        return Instances.getWorld().findUnsafeDoubleHole(pos);
    }

    // Block state queries

    /**
     * Checks if a block position contains air.
     *
     * @param pos The position to check
     * @return true if the block at position is air
     */
    public static boolean isAir(BlockPos pos) {
        return Instances.getWorld().isAir(pos);
    }

    /**
     * Checks if a position and the vertical space above it are composed entirely of Air.
     * <p>
     * This checks the block at {@code pos} and the blocks above it up to {@code height}.
     * </p>
     *
     * @param pos    The base position (floor level of the column).
     * @param height The total height of the column to check.
     * @return true if the block at {@code pos} and the blocks above it are Air.
     */
    public static boolean isClear(BlockPos pos, int height) {
        return Instances.getWorld().isClear(pos, height);
    }

    /**
     * Checks if a block can be replaced (is air or fluid).
     *
     * @param pos The position to check
     * @return true if the block can be replaced
     */
    public static boolean isReplaceable(BlockPos pos) {
        return Instances.getWorld().isReplaceable(pos);
    }

    /**
     * Checks if a block blocks movement (has a collision box).
     *
     * @param pos The position to check
     * @return true if the block blocks movement
     */
    public static boolean blocksMovement(BlockPos pos) {
        return Instances.getWorld().blocksMovement(pos);
    }

    /**
     * Checks if a block is solid (opaque and blocks light).
     *
     * @param pos The position to check
     * @return true if the block is solid
     */
    public static boolean isSolidBlock(BlockPos pos) {
        return Instances.getWorld().isSolidBlock(pos);
    }

    /**
     * Gets the block state at the given position.
     *
     * @param pos The position to get block state from
     * @return The block state at the position
     */
    public static BlockState getBlockState(BlockPos pos) {
        return Instances.getWorld().getBlockState(pos);
    }

    /**
     * Gets the block at the given position.
     *
     * @param pos The position to get block from
     * @return The block at the position
     */
    public static Block getBlock(BlockPos pos) {
        return Instances.getWorld().getBlock(pos);
    }

    /**
     * Gets the blast resistance of a block.
     * <p>
     * Higher values indicate more explosion-resistant blocks.
     * Bedrock has resistance 3,600,000.0F, obsidian has 1,200.0F.
     * </p>
     *
     * @param pos The position to check
     * @return The blast resistance value
     */
    public static double getBlastResistance(BlockPos pos) {
        return Instances.getWorld().getBlastResistance(pos);
    }

    // Block validation

    /**
     * Checks if a block is unbreakable.
     * <p>
     * Unbreakable blocks include bedrock, command blocks, barriers, etc.
     * </p>
     *
     * @param pos The position to check
     * @return true if the block cannot be broken
     */
    public static boolean isUnbreakable(BlockPos pos) {
        return Instances.getWorld().isUnbreakable(pos);
    }

    /**
     * Checks if a block can be broken.
     * <p>
     * Takes into account block hardness and game rules.
     * </p>
     *
     * @param pos The position to check
     * @return true if the block can be broken
     */
    public static boolean canBreak(BlockPos pos) {
        return Instances.getWorld().canBreak(pos);
    }

    /**
     * Checks if a block can be placed at the given position.
     * <p>
     * Takes into account block state and surrounding blocks.
     * </p>
     *
     * @param pos The position to check for placement
     * @return true if a block can be placed at this position
     */
    public static boolean canPlaceAt(BlockPos pos) {
        return Instances.getWorld().canPlaceAt(pos);
    }

    /**
     * Checks if a specific block can be placed at the given position.
     * <p>
     * Validates that the block placement follows Minecraft's placement rules.
     * </p>
     *
     * @param pos The position to check
     * @param block The block to place
     * @return true if the block can be placed at this position
     */
    public static boolean isValidPlacement(BlockPos pos, Block block) {
        return Instances.getWorld().isValidPlacement(pos, block);
    }

    // World boundaries

    /**
     * Checks if a position is within the world's valid boundaries.
     *
     * @param pos The position to check
     * @return true if the position is within world bounds
     */
    public static boolean isInWorldBounds(BlockPos pos) {
        return Instances.getWorld().isInWorldBounds(pos);
    }

    /**
     * Checks if the chunk region containing the position is loaded.
     *
     * @param pos The position to check
     * @return true if the region is loaded
     */
    public static boolean isRegionLoaded(BlockPos pos) {
        return Instances.getWorld().isRegionLoaded(pos);
    }

    /**
     * Gets the current render distance.
     *
     * @return The render distance in chunks
     */
    public static int getRenderDistance() {
        return Instances.getWorld().getRenderDistance();
    }

    /**
     * Checks if a position is within render distance.
     *
     * @param pos The position to check
     * @return true if the position is within render distance
     */
    public static boolean isInRenderDistance(BlockPos pos) {
        return Instances.getWorld().isInRenderDistance(pos);
    }

    // Mining

    /**
     * Checks if a block can be mined.
     * <p>
     * Takes into account block hardness, tools available, and game rules.
     * </p>
     *
     * @param pos The position to check
     * @return true if the block can be mined
     */
    public static boolean canMine(BlockPos pos) {
        return Instances.getWorld().canMine(pos);
    }

    /**
     * Gets the hardness of a block.
     * <p>
     * Hardness determines how long it takes to break the block.
     * </p>
     *
     * @param pos The position to check
     * @return The block hardness value
     */
    public static float getHardness(BlockPos pos) {
        return Instances.getWorld().getHardness(pos);
    }

    /**
     * Checks if a block is currently being mined by the mining system.
     *
     * @param pos The position to check
     * @return true if the block is being broken
     */
    public static boolean isBeingMined(BlockPos pos) {
        return Instances.getWorld().isBeingMined(pos, false);
    }

    /**
     * Checks if a block is currently being mined, with option for predicted mining.
     *
     * @param pos The position to check
     * @param predicted Whether to include predicted mining data (client-side packets)
     * @return true if the block is being broken
     */
    public static boolean isBeingMined(BlockPos pos, boolean predicted) {
        return Instances.getWorld().isBeingMined(pos, predicted);
    }

    // Block entities

    /**
     * Checks if a position has a block entity.
     *
     * @param pos The position to check
     * @return true if the position has a block entity
     */
    public static boolean hasBlockEntity(BlockPos pos) {
        return Instances.getWorld().hasBlockEntity(pos);
    }

    /**
     * Gets the block entity at the given position.
     *
     * @param pos The position to get block entity from
     * @return The block entity, or null if none exists
     */
    public static BlockEntity getBlockEntity(BlockPos pos) {
        return Instances.getWorld().getBlockEntity(pos);
    }

    /**
     * Gets all block entities in the world.
     *
     * @return An iterable of all block entities
     */
    public static Iterable<BlockEntity> getBlockEntities() {
        return Instances.getWorld().getBlockEntities();
    }

    // Raycasting

    /**
     * Performs a raycast between two points in the world.
     *
     * @param start The starting position
     * @param end The ending position
     * @return The block hit result, or null if no block was hit
     */
    public static BlockHitResult raycast(Vec3 start, Vec3 end) {
        return Instances.getRayCasting().raycast(start, end);
    }

    /**
     * Finds a visible point on a block's surface from the given eye position.
     * <p>
     * Calculates the closest visible point on the block's surface
     * that would be visible from the eye position.
     * </p>
     *
     * @param pos The block position
     * @param eyePos The eye position to check visibility from
     * @return A visible point on the block surface
     */
    public static Vec3 findVisiblePointOnBlock(BlockPos pos, Vec3 eyePos) {
        return Instances.getWorld().findVisiblePointOnBlock(pos, eyePos);
    }
}
