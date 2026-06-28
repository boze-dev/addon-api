package dev.boze.api.internal.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiPredicate;

public interface IAutoMine {
    boolean addTask(BlockPos pos, boolean rayCast, double range);

    boolean addActiveTask(BlockPos pos, boolean rayCast, double range);

    boolean addToQueue(BlockPos pos, boolean rayCast, double range);

    boolean canAddTask();

    int getActiveTaskCount();

    int getQueueSize();

    void setCanBreak(BiPredicate<BlockPos, BlockState> predicate);

    void resetCanBreak();
}
