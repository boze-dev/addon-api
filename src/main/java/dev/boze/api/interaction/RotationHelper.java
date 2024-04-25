package dev.boze.api.interaction;

import dev.boze.api.internal.Instances;
import net.minecraft.util.math.Vec3d;

/**
 * RotationHelper
 * <p>
 * Provides methods to calculate rotations
 */
public final class RotationHelper {

    /**
     * @param to the vector to calculate the angle to
     *
     * @return the angle between the player's eyes and the given vector
     */
    public static Rotation calculateAngle(Vec3d to) {
        return Instances.getInteraction().calculateAngle(to);
    }

    /**
     * @param from the vector to calculate the angle from
     * @param to the vector to calculate the angle to
     *
     * @return the angle between the given vectors
     */
    public static Rotation calculateAngle(Vec3d from, Vec3d to) {
        return Instances.getInteraction().calculateAngle(from, to);
    }
}
