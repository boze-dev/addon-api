package dev.boze.api.internal.interfaces;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public interface IMath {
    float[] calculateRotation(Vec3 eyes, Vec3 target);

    Vec3 getRotationVector(float yaw, float pitch);

    float normalizeAngle(float angle);

    Vec3 yawToVector(float yaw, double speed);

    Vec3 getDirectionalSpeed(double speed);

    // Interpolation
    double lerp(double delta, double start, double end);

    float lerp(float delta, float start, float end);

    Vec3 lerp(double delta, Vec3 start, Vec3 end);

    double bringCloser(double value, double goal, double increment);

    double clamp(double value, double min, double max);

    int clamp(int value, int min, int max);

    Vec3 clampToBox(Vec3 point, AABB box);

    Vec3 closestPointToBox(Vec3 point, AABB box);

    Vec3 normalize(Vec3 vector);

    double dotProduct(Vec3 a, Vec3 b);

    Vec3 crossProduct(Vec3 a, Vec3 b);

    double distance(Vec3 a, Vec3 b);

    double degreesToRadians(double degrees);

    double radiansToDegrees(double radians);

    double angleDifference(double angle1, double angle2);

    Vec3 findClosestPointOnBox(AABB box, Vec3 point);

    Vec3 getBestAimPoint(AABB box);

    boolean isPointInBox(Vec3 point, AABB box);

    double fastSin(double radians);

    double fastCos(double radians);
}
