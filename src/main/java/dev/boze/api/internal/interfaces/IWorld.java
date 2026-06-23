package dev.boze.api.internal.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public interface IWorld {

    boolean isHole(BlockPos pos, boolean doubles);

    boolean isSafeHole(BlockPos pos, boolean doubles);

    boolean isUnsafeHole(BlockPos pos, boolean doubles);

    BlockPos findDoubleHole(BlockPos pos);

    BlockPos findSafeDoubleHole(BlockPos pos);

    BlockPos findUnsafeDoubleHole(BlockPos pos);

    boolean isAir(BlockPos pos);

    boolean isClear(BlockPos pos, int height);

    boolean isReplaceable(BlockPos pos);

    boolean blocksMovement(BlockPos pos);

    boolean isSolidBlock(BlockPos pos);

    BlockState getBlockState(BlockPos pos);

    Block getBlock(BlockPos pos);

    double getBlastResistance(BlockPos pos);

    boolean isUnbreakable(BlockPos pos);

    boolean canBreak(BlockPos pos);

    boolean canPlaceAt(BlockPos pos);

    boolean isValidPlacement(BlockPos pos, Block block);

    boolean isInWorldBounds(BlockPos pos);

    boolean isRegionLoaded(BlockPos pos);

    int getRenderDistance();

    boolean isInRenderDistance(BlockPos pos);

    boolean canMine(BlockPos pos);

    float getHardness(BlockPos pos);

    boolean isBeingMined(BlockPos pos, boolean predicted);

    boolean hasBlockEntity(BlockPos pos);

    BlockEntity getBlockEntity(BlockPos pos);

    Iterable<BlockEntity> getBlockEntities();

    Vec3 findVisiblePointOnBlock(BlockPos pos, Vec3 eyePos);
}
