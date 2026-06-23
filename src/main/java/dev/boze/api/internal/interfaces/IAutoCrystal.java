package dev.boze.api.internal.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

public interface IAutoCrystal {
    BlockPos getPos();
    LivingEntity getTarget();
    double getDamage();
    float getCPS();
    float getMainCycleMs();
    float getAttackCycleMs();
}
