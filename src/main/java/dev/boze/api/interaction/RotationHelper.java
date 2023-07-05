package dev.boze.api.interaction;

import net.minecraft.util.math.Vec3d;

/**
 * RotationHelper
 *
 * Provides methods to calculate rotations
 */
public interface RotationHelper {

    /**
     * @param to the vector to calculate the angle to
     *
     * @return the angle between the player's eyes and the given vector
     */
    Rotation calculateAngle(Vec3d to);

    /**
     * @param from the vector to calculate the angle from
     * @param to the vector to calculate the angle to
     *
     * @return the angle between the given vectors
     */
    Rotation calculateAngle(Vec3d from, Vec3d to);
}
