package dev.boze.api.client.module.helper;

import dev.boze.api.internal.Instances;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

public class AutoMineHelper {

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
}
