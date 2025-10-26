package dev.boze.api.utility;

import dev.boze.api.internal.Instances;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * WorldHelper provides world and block-related utilities.
 * <p></p>
 * This class offers comprehensive utilities for interacting with the Minecraft world,
 * including hole detection, block state queries, placement validation, and mining operations.
 * All methods handle null positions and world safety appropriately.
 */
public class WorldHelper {

    // Hole detection

    /**
     * Checks if a position is a hole.
     * <p></p>
     * A hole is surrounded by solid blocks on all horizontal sides and below,
     * with air blocks at the hole position and above it.
     *
     * @param pos The position to check
     * @return true if the position is a hole
     */
    public static boolean isHole(BlockPos pos) {
        return Instances.getWorld().isHole(pos);
    }

    /**
     * Checks if a position is a hole, with option for double holes.
     * <p></p>
     * @param pos The position to check
     * @param doubles Whether to check for double holes
     * @return true if the position is a hole
     */
    public static boolean isHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isHole(pos, doubles);
    }

    /**
     * Checks if a position is a safe (bedrock) hole.
     * <p></p>
     * A safe hole is surrounded by bedrock blocks on all sides.
     * This provides maximum protection from crystal damage.
     *
     * @param pos The position to check
     * @return true if the position is a safe bedrock hole
     */
    public static boolean isSafeHole(BlockPos pos) {
        return Instances.getWorld().isSafeHole(pos, false);
    }

    /**
     * Checks if a position is an unsafe (obsidian) hole.
     * <p></p>
     * An unsafe hole is surrounded by obsidian/crying obsidian blocks.
     * This provides less protection than bedrock but is still commonly used.
     *
     * @param pos The position to check
     * @return true if the position is an unsafe obsidian hole
     */
    public static boolean isUnsafeHole(BlockPos pos) {
        return Instances.getWorld().isUnsafeHole(pos, false);
    }

    /**
     * Checks if a position is a safe (bedrock) hole, with option for double holes.
     * <p></p>
     * A safe hole is surrounded by bedrock blocks on all sides.
     * When doubles is true, checks for 3-block-high holes instead of 2-block-high.
     *
     * @param pos The position to check
     * @param doubles Whether to check for double holes
     * @return true if the position is a safe bedrock hole
     */
    public static boolean isSafeHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isSafeHole(pos, doubles);
    }

    /**
     * Checks if a position is an unsafe (obsidian) hole, with option for double holes.
     * <p></p>
     * An unsafe hole is surrounded by obsidian/crying obsidian blocks.
     * When doubles is true, checks for 3-block-high holes instead of 2-block-high.
     *
     * @param pos The position to check
     * @param doubles Whether to check for double holes
     * @return true if the position is an unsafe obsidian hole
     */
    public static boolean isUnsafeHole(BlockPos pos, boolean doubles) {
        return Instances.getWorld().isUnsafeHole(pos, doubles);
    }

    /**
     * Finds a connected double hole adjacent to the given position.
     * <p></p>
     * Searches west, east, north, and south positions for a connected hole
     * that forms a double hole pattern with the given position.
     *
     * @param pos The reference position
     * @return The position of the connected hole, or null if none found
     */
    public static BlockPos findDoubleHole(BlockPos pos) {
        return Instances.getWorld().findDoubleHole(pos);
    }

    /**
     * Finds a connected safe double hole adjacent to the given position.
     * <p></p>
     * Searches adjacent positions (west, east, north, south) for a safe hole
     * that forms a double hole pattern with the reference position.
     *
     * @param pos The reference position
     * @return The position of the connected safe hole, or null if none found
     */
    public static BlockPos findSafeDoubleHole(BlockPos pos) {
        return Instances.getWorld().findSafeDoubleHole(pos);
    }

    /**
     * Finds a connected unsafe double hole adjacent to the given position.
     * <p></p>
     * Searches adjacent positions (west, east, north, south) for an unsafe hole
     * that forms a double hole pattern with the reference position.
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
     * Checks if a block can be replaced (is air or fluid).
     *
     * @param pos The position to check
     * @return true if the block can be replaced
     */
    public static boolean isReplaceable(BlockPos pos) {
        return Instances.getWorld().isReplaceable(pos);
    }

    /**
     * Checks if a block blocks movement (solid blocks).
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
     * <p></p>
     * Higher values indicate more explosion-resistant blocks.
     * Bedrock has resistance 3600000.0F, obsidian has 1200.0F.
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
     * <p></p>
     * Unbreakable blocks include bedrock, command blocks, barriers, etc.
     *
     * @param pos The position to check
     * @return true if the block cannot be broken
     */
    public static boolean isUnbreakable(BlockPos pos) {
        return Instances.getWorld().isUnbreakable(pos);
    }

    /**
     * Checks if a block can be broken.
     * <p></p>
     * Takes into account block hardness and game rules.
     *
     * @param pos The position to check
     * @return true if the block can be broken
     */
    public static boolean canBreak(BlockPos pos) {
        return Instances.getWorld().canBreak(pos);
    }

    /**
     * Checks if a block can be placed at the given position.
     * <p></p>
     * Takes into account block state and surrounding blocks.
     *
     * @param pos The position to check for placement
     * @return true if a block can be placed at this position
     */
    public static boolean canPlaceAt(BlockPos pos) {
        return Instances.getWorld().canPlaceAt(pos);
    }

    /**
     * Checks if a specific block can be placed at the given position.
     * <p></p>
     * Validates that the block placement follows Minecraft's placement rules.
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
     * <p></p>
     * Takes into account block hardness, tools available, and game rules.
     *
     * @param pos The position to check
     * @return true if the block can be mined
     */
    public static boolean canMine(BlockPos pos) {
        return Instances.getWorld().canMine(pos);
    }

    /**
     * Gets the hardness of a block.
     * <p></p>
     * Hardness determines how long it takes to break the block.
     *
     * @param pos The position to check
     * @return The block hardness value
     */
    public static float getHardness(BlockPos pos) {
        return Instances.getWorld().getHardness(pos);
    }

    /**
     * Checks if a block is currently being mined.
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
     * @param predicted Whether to include predicted mining data
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
    public static BlockHitResult raycast(Vec3d start, Vec3d end) {
        return Instances.getRayCasting().raycast(start, end);
    }

    /**
     * Finds a visible point on a block's surface from the given eye position.
     * <p></p>
     * Calculates the closest visible point on the block's surface
     * that would be visible from the eye position.
     *
     * @param pos The block position
     * @param eyePos The eye position to check visibility from
     * @return A visible point on the block surface
     */
    public static Vec3d findVisiblePointOnBlock(BlockPos pos, Vec3d eyePos) {
        return Instances.getWorld().findVisiblePointOnBlock(pos, eyePos);
    }
}
