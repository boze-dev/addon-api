package dev.boze.api.internal.interfaces;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public interface IMath {
    float[] calculateRotation(Vec3d eyes, Vec3d target);

    Vec3d getRotationVector(float yaw, float pitch);

    float normalizeAngle(float angle);

    Vec3d yawToVector(float yaw, double speed);

    Vec3d getDirectionalSpeed(double speed);

    // Interpolation
    double lerp(double delta, double start, double end);

    float lerp(float delta, float start, float end);

    Vec3d lerp(double delta, Vec3d start, Vec3d end);

    double bringCloser(double value, double goal, double increment);

    double clamp(double value, double min, double max);

    int clamp(int value, int min, int max);

    Vec3d clampToBox(Vec3d point, Box box);

    Vec3d closestPointToBox(Vec3d point, Box box);

    Vec3d normalize(Vec3d vector);

    double dotProduct(Vec3d a, Vec3d b);

    Vec3d crossProduct(Vec3d a, Vec3d b);

    double distance(Vec3d a, Vec3d b);

    double degreesToRadians(double degrees);

    double radiansToDegrees(double radians);

    double angleDifference(double angle1, double angle2);

    Vec3d findClosestPointOnBox(Box box, Vec3d point);

    Vec3d getBestAimPoint(Box box);

    boolean isPointInBox(Vec3d point, Box box);

    double fastSin(double radians);

    double fastCos(double radians);
}
