package dev.boze.api.utility;

import dev.boze.api.internal.Instances;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

/**
 * MathHelper provides comprehensive mathematical utilities.
 * <br>
 * This class consolidates useful mathematical operations,
 * including rotation calculations, vector operations, interpolation, and collision math.
 * All methods are optimized for performance and handle edge cases gracefully.
 */
public class MathHelper {

    public static final float[] EMPTY_ROTATION = {0, 0};

    // Rotation & Angles

    /**
     * Calculates the yaw and pitch rotation needed to face a target position.
     * <br>
     * Returns yaw and pitch angles in degrees required to point from eyes position
     * to target position. Yaw is normalized to -180 to 180 range.
     *
     * @param target The target position to face
     * @return float array containing [yaw, pitch] in degrees
     */
    public static float[] calculateRotation(Vec3 target) {
        return Instances.getMath().calculateRotation(Minecraft.getInstance().player.getEyePosition(), target);
    }

    /**
     * Calculates the yaw and pitch rotation needed to face a target position.
     * <br>
     * Returns yaw and pitch angles in degrees required to point from eyes position
     * to target position. Yaw is normalized to -180 to 180 range.
     *
     * @param eyes The eye position to calculate from
     * @param target The target position to face
     * @return float array containing [yaw, pitch] in degrees
     */
    public static float[] calculateRotation(Vec3 eyes, Vec3 target) {
        return Instances.getMath().calculateRotation(eyes, target);
    }

    /**
     * Calculates the yaw and pitch rotation needed to face a target position, or returns empty rotation.
     * <br>
     * Returns yaw and pitch angles in degrees required to point from eyes position
     * to target position if rotate is true. Returns EMPTY_ROTATION if rotate is false.
     * Yaw is normalized to -180 to 180 range.
     *
     * @param target The target position to face
     * @param rotate Whether to calculate rotation or return empty rotation
     * @return float array containing [yaw, pitch] in degrees, or EMPTY_ROTATION
     */
    public static float[] calculateRotation(Vec3 target, boolean rotate) {
        return rotate ? Instances.getMath().calculateRotation(Minecraft.getInstance().player.getEyePosition(), target) : EMPTY_ROTATION;
    }

    /**
     * Calculates the yaw and pitch rotation needed to face a target position, or returns empty rotation.
     * <br>
     * Returns yaw and pitch angles in degrees required to point from eyes position
     * to target position if rotate is true. Returns EMPTY_ROTATION if rotate is false.
     * Yaw is normalized to -180 to 180 range.
     *
     * @param eyes The eye position to calculate from
     * @param target The target position to face
     * @param rotate Whether to calculate rotation or return empty rotation
     * @return float array containing [yaw, pitch] in degrees, or EMPTY_ROTATION
     */
    public static float[] calculateRotation(Vec3 eyes, Vec3 target, boolean rotate) {
        return rotate ? Instances.getMath().calculateRotation(eyes, target) : EMPTY_ROTATION;
    }

    /**
     * Converts yaw and pitch angles to a normalized direction vector.
     * <br>
     * Creates a unit vector pointing in the direction specified by the yaw and pitch angles.
     *
     * @param yaw Yaw angle in degrees
     * @param pitch Pitch angle in degrees
     * @return Normalized direction vector
     */
    public static Vec3 getRotationVector(float yaw, float pitch) {
        return Instances.getMath().getRotationVector(yaw, pitch);
    }

    /**
     * Normalizes an angle to the range -180 to 180 degrees.
     * <br>
     * Ensures angle values stay within the standard Minecraft rotation range.
     *
     * @param angle Angle in degrees
     * @return Normalized angle in range -180 to 180
     */
    public static float normalizeAngle(float angle) {
        return Instances.getMath().normalizeAngle(angle);
    }

    /**
     * Converts yaw angle and speed to a movement vector.
     * <br>
     * Calculates the X and Z components of movement based on yaw direction and speed.
     * This is commonly used for directional movement in speed/packetfly modules.
     *
     * @param yaw Yaw angle in degrees
     * @param speed Movement speed
     * @return Vec3d with X and Z movement components (Y is always 0)
     */
    public static Vec3 yawToVector(float yaw, double speed) {
        return Instances.getMath().yawToVector(yaw, speed);
    }

    /**
     * Calculates directional speed from player input.
     * <br>
     * Takes the player's current input (WASD keys) and converts it to a movement vector
     * based on the player's facing direction.
     *
     * @param speed Base movement speed
     * @return Movement vector based on player input and facing direction
     */
    public static Vec3 getDirectionalSpeed(double speed) {
        return Instances.getMath().getDirectionalSpeed(speed);
    }

    // Interpolation

    /**
     * Linear interpolation between two double values.
     * <br>
     * @param delta Interpolation factor (0.0 = start, 1.0 = end)
     * @param start Starting value
     * @param end Ending value
     * @return Interpolated value
     */
    public static double lerp(double delta, double start, double end) {
        return Instances.getMath().lerp(delta, start, end);
    }

    /**
     * Linear interpolation between two float values.
     * <br>
     * @param delta Interpolation factor (0.0 = start, 1.0 = end)
     * @param start Starting value
     * @param end Ending value
     * @return Interpolated value
     */
    public static float lerp(float delta, float start, float end) {
        return Instances.getMath().lerp(delta, start, end);
    }

    /**
     * Linear interpolation between two vectors.
     * <br>
     * @param delta Interpolation factor (0.0 = start, 1.0 = end)
     * @param start Starting vector
     * @param end Ending vector
     * @return Interpolated vector
     */
    public static Vec3 lerp(double delta, Vec3 start, Vec3 end) {
        return Instances.getMath().lerp(delta, start, end);
    }

    /**
     * Gradually brings a value closer to a goal by a specified increment.
     * <br>
     * This is useful for smooth transitions and animations where you want
     * to approach a target value incrementally.
     *
     * @param value Current value
     * @param goal Target value
     * @param increment Maximum change per call
     * @return New value closer to goal
     */
    public static double bringCloser(double value, double goal, double increment) {
        return Instances.getMath().bringCloser(value, goal, increment);
    }

    // Clamping and bounding

    /**
     * Clamps a double value to a specified range.
     * <br>
     * @param value Value to clamp
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return Clamped value within [min, max]
     */
    public static double clamp(double value, double min, double max) {
        return Instances.getMath().clamp(value, min, max);
    }

    /**
     * Clamps an integer value to a specified range.
     * <br>
     * @param value Value to clamp
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return Clamped value within [min, max]
     */
    public static int clamp(int value, int min, int max) {
        return Instances.getMath().clamp(value, min, max);
    }

    /**
     * Clamps a point to stay within a box's boundaries.
     * <br>
     * @param point Point to clamp
     * @param box Bounding box
     * @return Point clamped to box boundaries
     */
    public static Vec3 clampToBox(Vec3 point, AABB box) {
        return Instances.getMath().clampToBox(point, box);
    }

    /**
     * Finds the closest point on a box's surface to a given point.
     * <br>
     * @param point Reference point
     * @param box Target box
     * @return Closest point on the box surface
     */
    public static Vec3 closestPointToBox(Vec3 point, AABB box) {
        return Instances.getMath().closestPointToBox(point, box);
    }

    // Vector operations

    /**
     * Normalizes a vector to unit length.
     * <br>
     * @param vector Vector to normalize
     * @return Normalized vector, or zero vector if input is zero-length
     */
    public static Vec3 normalize(Vec3 vector) {
        return Instances.getMath().normalize(vector);
    }

    /**
     * Calculates the dot product of two vectors.
     * <br>
     * @param a First vector
     * @param b Second vector
     * @return Dot product result
     */
    public static double dotProduct(Vec3 a, Vec3 b) {
        return Instances.getMath().dotProduct(a, b);
    }

    /**
     * Calculates the cross product of two vectors.
     * <br>
     * @param a First vector
     * @param b Second vector
     * @return Cross product result vector
     */
    public static Vec3 crossProduct(Vec3 a, Vec3 b) {
        return Instances.getMath().crossProduct(a, b);
    }

    /**
     * Calculates the distance between two points.
     * <br>
     * @param a First point
     * @param b Second point
     * @return Euclidean distance
     */
    public static double distance(Vec3 a, Vec3 b) {
        return Instances.getMath().distance(a, b);
    }

    // Angle conversions

    /**
     * Converts degrees to radians.
     * <br>
     * @param degrees Angle in degrees
     * @return Angle in radians
     */
    public static double degreesToRadians(double degrees) {
        return Instances.getMath().degreesToRadians(degrees);
    }

    /**
     * Converts radians to degrees.
     * <br>
     * @param radians Angle in radians
     * @return Angle in degrees
     */
    public static double radiansToDegrees(double radians) {
        return Instances.getMath().radiansToDegrees(radians);
    }

    /**
     * Calculates the smallest angle difference between two angles.
     * <br>
     * Returns a value between -180 and 180 degrees representing the
     * shortest rotation needed to go from angle1 to angle2.
     *
     * @param angle1 First angle in degrees
     * @param angle2 Second angle in degrees
     * @return Smallest angle difference in degrees
     */
    public static double angleDifference(double angle1, double angle2) {
        return Instances.getMath().angleDifference(angle1, angle2);
    }

    // Box/collision math

    /**
     * Finds the closest point on a box's surface to a given point.
     * <br>
     * @param box Target box
     * @param point Reference point
     * @return Closest point on the box surface
     */
    public static Vec3 findClosestPointOnBox(AABB box, Vec3 point) {
        return Instances.getMath().findClosestPointOnBox(box, point);
    }

    /**
     * Calculates the optimal aim point for a box.
     * <br>
     * Finds the best point to aim at on a box, considering the player's
     * current position and line of sight.
     *
     * @param box Target box
     * @return Optimal aim point on the box
     */
    public static Vec3 getBestAimPoint(AABB box) {
        return Instances.getMath().getBestAimPoint(box);
    }

    /**
     * Checks if a point is inside a box.
     * <br>
     * @param point Point to test
     * @param box Box to test against
     * @return true if point is inside the box
     */
    public static boolean isPointInBox(Vec3 point, AABB box) {
        return Instances.getMath().isPointInBox(point, box);
    }

    // Fast trigonometry

    /**
     * Fast sine calculation optimized for x86 processors.
     * <br>
     * Uses angle reduction to keep values within the safe range for
     * better performance and precision on x86 architecture.
     *
     * @param radians Angle in radians
     * @return Sine of the angle
     */
    public static double fastSin(double radians) {
        return Instances.getMath().fastSin(radians);
    }

    /**
     * Fast cosine calculation optimized for x86 processors.
     * <br>
     * Uses angle reduction to keep values within the safe range for
     * better performance and precision on x86 architecture.
     *
     * @param radians Angle in radians
     * @return Cosine of the angle
     */
    public static double fastCos(double radians) {
        return Instances.getMath().fastCos(radians);
    }
}
