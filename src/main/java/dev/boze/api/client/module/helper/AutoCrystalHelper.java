package dev.boze.api.client.module.helper;

import dev.boze.api.internal.Instances;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;

public class AutoCrystalHelper {

    /**
     * Gets the current position that AutoCrystal is placing at.
     *
     * @return The current place position, or null if no place
     */
    public static BlockPos getPos() {
        return Instances.getAutoCrystal().getPos();
    }

    /**
     * Gets the current target that AutoCrystal is attacking.
     *
     * @return The current target entity, or null if no target
     */
    public static LivingEntity getTarget() {
        return Instances.getAutoCrystal().getTarget();
    }

    /**
     * Gets the current damage that AutoCrystal is dealing to its target.
     *
     * @return The current damage value
     */
    public static double getDamage() {
        return Instances.getAutoCrystal().getDamage();
    }

    /**
     * Gets the current crystals per second (CPS) rate of AutoCrystal.
     *
     * @return The CPS value
     */
    public static float getCPS() {
        return Instances.getAutoCrystal().getCPS();
    }

    /**
     * Gets the time in milliseconds that the main cycle (break + place) takes.
     *
     * @return The main cycle time in milliseconds
     */
    public static float getMainCycleMs() {
        return Instances.getAutoCrystal().getMainCycleMs();
    }

    /**
     * Gets the time in milliseconds that the attack cycle takes.
     *
     * @return The attack cycle time in milliseconds
     */
    public static float getAttackCycleMs() {
        return Instances.getAutoCrystal().getAttackCycleMs();
    }
}
