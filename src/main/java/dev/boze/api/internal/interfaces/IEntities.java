package dev.boze.api.internal.interfaces;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;

public interface IEntities {
    boolean isWithinRange(Entity entity, double range);

    double getDistance(Entity from, Entity to);

    Vec3d getEyePos(Entity entity);

    boolean canSee(Entity from, Entity to);

    Vec3d getInterpolatedPos(Entity entity, float delta);

    float getHealth(Entity entity);

    float getMaxHealth(Entity entity);

    int getArmorDurability(Entity entity);

    boolean hasTotem(Entity entity);

    int getPing(PlayerEntity player);

    boolean isHostile(Entity entity);

    boolean isPassive(Entity entity);

    boolean isFriend(Entity entity);

    boolean isTarget(Entity entity);

    boolean isInHole(Entity entity, boolean doubles);

    boolean isBurrowed(Entity entity);

    boolean isAlive(Entity entity);

    boolean isInvisible(Entity entity);

    boolean isMoving(Entity entity);

    GameMode getGameMode(PlayerEntity player);
}
