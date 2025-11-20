package dev.boze.api.internal.interfaces;

import net.minecraft.entity.LivingEntity;

public interface IAutoCrystal {
    LivingEntity getTarget();
    double getDamage();
    float getCPS();
    float getMainCycleMs();
    float getAttackCycleMs();
}
