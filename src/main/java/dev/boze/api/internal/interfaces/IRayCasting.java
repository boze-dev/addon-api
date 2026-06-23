package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.interaction.InteractionMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public interface IRayCasting {
    BlockHitResult cast(BlockPos pos, InteractionMode mode, double range, double wallsRange, boolean strictDirection, boolean airPlace);

    BlockHitResult raycast(Vec3 start, Vec3 end);
}
