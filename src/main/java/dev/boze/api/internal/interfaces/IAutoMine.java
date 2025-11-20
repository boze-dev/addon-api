package dev.boze.api.internal.interfaces;

import net.minecraft.util.math.BlockPos;

public interface IAutoMine {
    boolean addTask(BlockPos pos, boolean rayCast, double range);
}
