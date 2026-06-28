package dev.boze.api.client.module.helper;

import dev.boze.api.internal.Instances;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiPredicate;

public class AutoMineHelper {

    /**
     * Sets a custom predicate that decides whether AutoMine may break a block.
     * <br>
     * The predicate is consulted in addition to AutoMine's normal breakability check: a block is
     * mineable if it is normally breakable OR the predicate returns true for it. This lets an addon
     * allow AutoMine to mine blocks it would otherwise skip (e.g. bedrock). Range and filter checks
     * still apply. Only one predicate may be set at a time; setting a new one replaces the previous.
     *
     * @param predicate Tests a block position and its state; return true to additionally allow it
     */
    public static void setCanBreak(BiPredicate<BlockPos, BlockState> predicate) {
        Instances.getAutoMine().setCanBreak(predicate);
    }

    /**
     * Removes any custom canBreak predicate, restoring AutoMine's default breakability behavior.
     */
    public static void resetCanBreak() {
        Instances.getAutoMine().resetCanBreak();
    }

    /**
     * Adds a mining task to AutoMine using default settings.
     * Uses rayCast=false and player's block interaction range.
     *
     * @param pos The block position to mine
     * @return true if the task was successfully added
     */
    public static boolean addTask(BlockPos pos) {
        return Instances.getAutoMine().addTask(pos, false, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Adds a mining task to AutoMine with custom rayCast setting.
     * Uses player's block interaction range.
     *
     * @param pos The block position to mine
     * @param rayCast Whether to raycast through walls
     * @return true if the task was successfully added
     */
    public static boolean addTask(BlockPos pos, boolean rayCast) {
        return Instances.getAutoMine().addTask(pos, rayCast, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Adds a mining task to AutoMine with custom range setting.
     * Uses rayCast=false by default.
     *
     * @param pos The block position to mine
     * @param range The maximum mining range
     * @return true if the task was successfully added
     */
    public static boolean addTask(BlockPos pos, double range) {
        return Instances.getAutoMine().addTask(pos, false, range);
    }

    /**
     * Adds a mining task to AutoMine with custom rayCast and range settings.
     *
     * @param pos The block position to mine
     * @param rayCast Whether to raycast through walls
     * @param range The maximum mining range
     * @return true if the task was successfully added
     */
    public static boolean addTask(BlockPos pos, boolean rayCast, double range) {
        return Instances.getAutoMine().addTask(pos, rayCast, range);
    }

    /**
     * Starts mining a block in one of AutoMine's active slots.
     * <br>
     * If nothing is being mined this becomes the first (and only) block; with the DoubleMine setting
     * enabled and one block already mining, it becomes a second simultaneous block. Unlike
     * {@link #addTask}, this works whether or not AutoMine is already mining. Use {@link #canAddTask()}
     * to check capacity first, or {@link #addToQueue} for overflow. Uses rayCast=false and the
     * player's block interaction range.
     *
     * @param pos The block position to mine
     * @return true if the block started mining, false if it couldn't be (unbreakable, already
     * queued/mining, or no slot is free right now)
     */
    public static boolean addActiveTask(BlockPos pos) {
        return addActiveTask(pos, false, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Starts mining a block in one of AutoMine's active slots, with a custom rayCast setting.
     * Uses the player's block interaction range. See {@link #addActiveTask(BlockPos)}.
     *
     * @param pos The block position to mine
     * @param rayCast Whether to raycast through walls
     * @return true if the block started mining
     */
    public static boolean addActiveTask(BlockPos pos, boolean rayCast) {
        return addActiveTask(pos, rayCast, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Starts mining a block in one of AutoMine's active slots, with a custom range. Uses rayCast=false.
     * See {@link #addActiveTask(BlockPos)}.
     *
     * @param pos The block position to mine
     * @param range The maximum mining range
     * @return true if the block started mining
     */
    public static boolean addActiveTask(BlockPos pos, double range) {
        return addActiveTask(pos, false, range);
    }

    /**
     * Starts mining a block in one of AutoMine's active slots, with custom rayCast and range settings.
     * See {@link #addActiveTask(BlockPos)}.
     *
     * @param pos The block position to mine
     * @param rayCast Whether to raycast through walls
     * @param range The maximum mining range
     * @return true if the block started mining
     */
    public static boolean addActiveTask(BlockPos pos, boolean rayCast, double range) {
        return Instances.getAutoMine().addActiveTask(pos, rayCast, range);
    }

    /**
     * Adds a block to AutoMine's mining queue, to be mined after the current blocks.
     * <br>
     * Uses rayCast=false and the player's block interaction range.
     *
     * @param pos The block position to queue
     * @return true if the block was queued, false if it couldn't be (unbreakable, already
     * queued/mining, or the queue is full)
     */
    public static boolean addToQueue(BlockPos pos) {
        return addToQueue(pos, false, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Adds a block to AutoMine's mining queue with a custom rayCast setting.
     * Uses the player's block interaction range.
     *
     * @param pos The block position to queue
     * @param rayCast Whether to raycast through walls
     * @return true if the block was queued
     */
    public static boolean addToQueue(BlockPos pos, boolean rayCast) {
        return addToQueue(pos, rayCast, Minecraft.getInstance().player.blockInteractionRange());
    }

    /**
     * Adds a block to AutoMine's mining queue with a custom range.
     * Uses rayCast=false.
     *
     * @param pos The block position to queue
     * @param range The maximum mining range
     * @return true if the block was queued
     */
    public static boolean addToQueue(BlockPos pos, double range) {
        return addToQueue(pos, false, range);
    }

    /**
     * Adds a block to AutoMine's mining queue with custom rayCast and range settings.
     *
     * @param pos The block position to queue
     * @param rayCast Whether to raycast through walls
     * @param range The maximum mining range
     * @return true if the block was queued
     */
    public static boolean addToQueue(BlockPos pos, boolean rayCast, double range) {
        return Instances.getAutoMine().addToQueue(pos, rayCast, range);
    }

    /**
     * Checks whether {@link #addActiveTask} would currently accept another block, i.e. AutoMine is
     * enabled and a mining slot is free (accounting for the DoubleMine setting and mining progress).
     *
     * @return true if a block can be started right now
     */
    public static boolean canAddTask() {
        return Instances.getAutoMine().canAddTask();
    }

    /**
     * @return the number of blocks AutoMine is actively mining right now (0, or up to 2 with DoubleMine)
     */
    public static int getActiveTaskCount() {
        return Instances.getAutoMine().getActiveTaskCount();
    }

    /**
     * @return the number of blocks currently waiting in AutoMine's mining queue
     */
    public static int getQueueSize() {
        return Instances.getAutoMine().getQueueSize();
    }
}
