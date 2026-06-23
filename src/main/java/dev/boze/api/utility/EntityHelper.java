package dev.boze.api.utility;

import dev.boze.api.internal.Instances;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;

/**
 * EntityHelper provides entity utility methods.
 * <br>
 * This class offers a comprehensive set of utilities for working with Minecraft entities,
 * including distance calculations, health checks, entity type validation, and status queries.
 * <br>
 * All methods handle null entities gracefully and provide safe defaults.
 */
public class EntityHelper {

    // Distance & Position

    /**
     * Checks if an entity is within range of the player.
     * <br>
     * This method calculates the distance from the player to the specified entity
     * and returns true if the entity is within the given range.
     *
     * @param entity The entity to check distance for
     * @param range The maximum distance in blocks
     * @return true if the entity is within range, false otherwise
     */
    public static boolean isWithinRange(Entity entity, double range) {
        return Instances.getEntities().isWithinRange(entity, range);
    }

    /**
     * Checks if an entity is within range of another entity.
     * <br>
     * This method calculates the distance between the two entities
     * and returns true if the target entity is within the given range.
     *
     * @param entity The entity to check distance for
     * @param from The entity to measure distance from
     * @param range The maximum distance in blocks
     * @return true if the entity is within range, false otherwise
     */
    public static boolean isWithinRange(Entity entity, Entity from, double range) {
        return Instances.getEntities().getDistance(from, entity) <= range;
    }

    /**
     * Gets the distance between two entities.
     * <br>
     * Calculates the Euclidean distance between the positions of two entities.
     * Returns Double.MAX_VALUE if either entity is null.
     *
     * @param from The first entity
     * @param to The second entity
     * @return The distance in blocks, or Double.MAX_VALUE if either entity is null
     */
    public static double getDistance(Entity from, Entity to) {
        return Instances.getEntities().getDistance(from, to);
    }

    /**
     * Gets the eye position of an entity.
     * <br>
     * Returns the position of the entity's eyes, which is typically used for
     * line-of-sight calculations and targeting. Returns Vec3d.ZERO if the entity is null.
     *
     * @param entity The entity to get eye position for
     * @return The eye position as a Vec3d, or Vec3d.ZERO if entity is null
     */
    public static Vec3 getEyePos(Entity entity) {
        return Instances.getEntities().getEyePos(entity);
    }

    /**
     * Checks if one entity can see another entity.
     * <br>
     * Performs a line-of-sight check between two entities by casting a ray
     * from the first entity's eyes to the second entity's eyes.
     * Returns false if either entity is null.
     *
     * @param from The entity doing the seeing
     * @param to The entity being seen
     * @return true if there's a clear line of sight, false otherwise
     */
    public static boolean canSee(Entity from, Entity to) {
        return Instances.getEntities().canSee(from, to);
    }

    /**
     * Gets the interpolated position of an entity for smooth rendering.
     * <br>
     * Calculates the interpolated position between the entity's previous position
     * and current position using the specified delta value. A delta of 0.0F returns
     * the previous frame position, while 1.0F returns the current position.
     * This is useful for smooth rendering animations that occur between ticks.
     * Returns Vec3d.ZERO if the entity is null.
     *
     * @param entity The entity to get interpolated position for
     * @param delta The interpolation factor between 0.0F and 1.0F
     * @return The interpolated position as a Vec3d, or Vec3d.ZERO if entity is null
     */
    public static Vec3 getInterpolatedPos(Entity entity, float delta) {
        return Instances.getEntities().getInterpolatedPos(entity, delta);
    }

    // Health & Combat

    /**
     * Gets the total health of an entity.
     * <br>
     * Returns the entity's current health plus any absorption amount.
     * For non-living entities, returns 0.0F.
     *
     * @param entity The entity to get health for
     * @return The total health value, or 0.0F if not a living entity
     */
    public static float getHealth(Entity entity) {
        return Instances.getEntities().getHealth(entity);
    }

    /**
     * Gets the maximum health of an entity.
     * <br>
     * Returns the entity's maximum health capacity.
     * For non-living entities, returns 0.0F.
     *
     * @param entity The entity to get max health for
     * @return The maximum health value, or 0.0F if not a living entity
     */
    public static float getMaxHealth(Entity entity) {
        return Instances.getEntities().getMaxHealth(entity);
    }

    /**
     * Gets the total durability of all armor items worn by an entity.
     * <br>
     * Calculates the sum of remaining durability (max damage - current damage)
     * for all armor pieces (helmet, chestplate, leggings, boots).
     * Only counts humanoid armor slots, not horse armor.
     * For non-living entities, returns 0.
     *
     * @param entity The entity to get armor durability for
     * @return The total remaining durability of all armor pieces, or 0 if not a living entity
     */
    public static int getArmorDurability(Entity entity) {
        return Instances.getEntities().getArmorDurability(entity);
    }

    /**
     * Checks if an entity has a Totem of Undying in their offhand.
     * <br>
     * Returns true if the entity is holding a Totem of Undying in their offhand slot.
     * For non-living entities, returns false.
     *
     * @param entity The entity to check
     * @return true if the entity has a totem in offhand, false otherwise
     */
    public static boolean hasTotem(Entity entity) {
        return Instances.getEntities().hasTotem(entity);
    }

    /**
     * Gets the ping (latency) of a player in milliseconds.
     * <br>
     * Retrieves the player's network latency from the tab list.
     * Returns 0 if the player is not found in the tab list.
     *
     * @param player The player to get ping for
     * @return The ping in milliseconds, or 0 if player is not in tab list
     */
    public static int getPing(Player player) {
        return Instances.getEntities().getPing(player);
    }

    // Entity Type Checks

    /**
     * Checks if an entity is hostile.
     * <br>
     * Returns true if the entity belongs to the MONSTER spawn group
     * (e.g., zombies, skeletons, creepers, etc.).
     *
     * @param entity The entity to check
     * @return true if the entity is hostile, false otherwise
     */
    public static boolean isHostile(Entity entity) {
        return Instances.getEntities().isHostile(entity);
    }

    /**
     * Checks if an entity is passive/animal.
     * <br>
     * Returns true if the entity belongs to passive spawn groups
     * (CREATURE, WATER_AMBIENT, WATER_CREATURE, UNDERGROUND_WATER_CREATURE, AXOLOTLS, AMBIENT)
     * or is a special case like villagers.
     *
     * @param entity The entity to check
     * @return true if the entity is passive/animal, false otherwise
     */
    public static boolean isPassive(Entity entity) {
        return Instances.getEntities().isPassive(entity);
    }

    /**
     * Checks if an entity is friended.
     * <br>
     * Returns true if the entity is a player that has been added to the friends list.
     * For non-player entities, always returns false.
     *
     * @param entity The entity to check
     * @return true if the entity is friended, false otherwise
     */
    public static boolean isFriend(Entity entity) {
        return Instances.getEntities().isFriend(entity);
    }

    /**
     * Checks if an entity is currently targeted.
     * <br>
     * Returns true if the entity is in the current target list,
     * meaning it has been attacked recently and is being tracked.
     *
     * @param entity The entity to check
     * @return true if the entity is currently targeted, false otherwise
     */
    public static boolean isTarget(Entity entity) {
        return Instances.getEntities().isTarget(entity);
    }

    /**
     * Checks if an entity is in a hole (surrounded by blocks).
     * <br>
     * A hole is defined as being surrounded by solid blocks on all horizontal sides
     * and having a solid block below. When doubles is false, checks for single-layer holes only.
     * When doubles is true, checks for double-layer hole patterns.
     *
     * @param entity The entity to check
     * @param doubles Whether to check for double-layer holes (allows some air blocks)
     * @return true if the entity is in a hole, false otherwise
     */
    public static boolean isInHole(Entity entity, boolean doubles) {
        return Instances.getEntities().isInHole(entity, doubles);
    }

    /**
     * Checks if an entity is burrowed (inside a block).
     * <br>
     * Returns true if the entity's position is inside a solid block
     * like obsidian, ender chests, or bedrock.
     *
     * @param entity The entity to check
     * @return true if the entity is burrowed, false otherwise
     */
    public static boolean isBurrowed(Entity entity) {
        return Instances.getEntities().isBurrowed(entity);
    }

    // Status & State

    /**
     * Checks if an entity is alive.
     * <br>
     * Returns true if the entity exists and is not dead.
     * This is a safer check than just calling isAlive() on potentially null entities.
     *
     * @param entity The entity to check
     * @return true if the entity exists and is alive, false otherwise
     */
    public static boolean isAlive(Entity entity) {
        return Instances.getEntities().isAlive(entity);
    }

    /**
     * Checks if an entity is invisible.
     * <br>
     * Returns true if the entity has the invisibility effect or is otherwise invisible.
     * For null entities, returns false.
     *
     * @param entity The entity to check
     * @return true if the entity is invisible, false otherwise
     */
    public static boolean isInvisible(Entity entity) {
        return Instances.getEntities().isInvisible(entity);
    }

    /**
     * Checks if an entity is currently moving.
     * <br>
     * Determines movement by checking if the entity's position has changed
     * significantly (> 0.001 blocks) from its previous position.
     * For null entities, returns false.
     *
     * @param entity The entity to check
     * @return true if the entity is moving, false otherwise
     */
    public static boolean isMoving(Entity entity) {
        return Instances.getEntities().isMoving(entity);
    }

    /**
     * Gets the game mode of a player.
     * <br>
     * Retrieves the player's current game mode from the tab list.
     * Returns null if the player is not found in the tab list or
     * if the player list hasn't loaded yet.
     *
     * @param player The player to get game mode for
     * @return The player's game mode, or null if not available
     */
    public static GameType getGameMode(Player player) {
        return Instances.getEntities().getGameMode(player);
    }
}
