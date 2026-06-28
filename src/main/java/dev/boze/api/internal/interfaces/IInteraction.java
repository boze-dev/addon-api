package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.interaction.InteractionMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

public interface IInteraction {
    boolean place(InteractionMode mode, BlockHitResult hitResult, InteractionHand hand);

    boolean isEmpty(BlockPos pos);

    boolean canBreak(BlockPos pos);

    BlockHitResult breakCast(BlockPos pos, double range, boolean throughWalls);

    boolean breakBlock(BlockPos pos, double range, boolean throughWalls);
}
