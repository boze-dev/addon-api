package dev.boze.api.internal.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

public interface IEntities {
    boolean isWithinRange(Entity entity, double range);

    double getDistance(Entity from, Entity to);

    Vec3 getEyePos(Entity entity);

    boolean canSee(Entity from, Entity to);

    Vec3 getInterpolatedPos(Entity entity, float delta);

    float getHealth(Entity entity);

    float getMaxHealth(Entity entity);

    int getArmorDurability(Entity entity);

    boolean hasTotem(Entity entity);

    int getPing(Player player);

    boolean isHostile(Entity entity);

    boolean isPassive(Entity entity);

    boolean isFriend(Entity entity);

    boolean isTarget(Entity entity);

    boolean isInHole(Entity entity, boolean doubles);

    boolean isBurrowed(Entity entity);

    boolean isAlive(Entity entity);

    boolean isInvisible(Entity entity);

    boolean isMoving(Entity entity);

    GameType getGameMode(Player player);
}
