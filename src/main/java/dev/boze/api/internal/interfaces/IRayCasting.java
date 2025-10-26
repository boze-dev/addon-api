package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.interaction.InteractionMode;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public interface IRayCasting {
    BlockHitResult cast(BlockPos pos, InteractionMode mode, double range, double wallsRange, boolean strictDirection);

    BlockHitResult raycast(Vec3d start, Vec3d end);
}
