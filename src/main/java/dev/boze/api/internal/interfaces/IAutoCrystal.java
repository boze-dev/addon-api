package dev.boze.api.internal.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

public interface IAutoCrystal {
    BlockPos getPos();
    LivingEntity getTarget();
    double getDamage();
    float getCPS();
    float getMainCycleMs();
    float getAttackCycleMs();
}
