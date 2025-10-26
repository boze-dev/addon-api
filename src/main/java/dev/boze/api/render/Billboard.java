package dev.boze.api.render;

import dev.boze.api.internal.Instances;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec3d;

/**
 * Billboard rendering API for displaying 2D HUD elements at 3D world positions
 * <p></p>
 * Provides methods to render 2D graphics (lines, quads, text) that always face the camera
 * at specified 3D world positions. Use with HudDrawer and TextDrawer for rendering content.
 */
public class Billboard {

    /**
     * Starts billboard rendering at a 3D world position with distance-based scaling
     * <p></p>
     * The scale factor is calculated based on distance from camera using:
     * scale = clamp(1.0 / distance, minScale, maxScale) * factor * 3.0
     * <p></p>
     * This mode scales elements based on their distance from the camera,
     * making closer elements appear larger and farther elements appear smaller.
     * <p></p>
     * Returns false if the position is not visible on screen
     *
     * @param worldPosition The 3D world position to render at
     * @param drawContext The draw context to use for matrix operations
     * @param factor Base scale multiplier
     * @param minScale Minimum allowed scale value
     * @param maxScale Maximum allowed scale value
     * @return true if position is visible and rendering started, false otherwise
     */
    public static boolean start(Vec3d worldPosition, DrawContext drawContext, double factor, double minScale, double maxScale) {
        return Instances.getBillboard().start(worldPosition, drawContext, factor, minScale, maxScale);
    }

    /**
     * Starts billboard rendering at a 3D world position with constant scaling
     * <p></p>
     * The scale factor is constant regardless of distance from camera using:
     * scale = clamp(1 - distance * 0.01, 0.5, MAX_VALUE) * scale
     * <p></p>
     * This mode maintains consistent element sizes regardless of distance,
     * with a slight fade-out effect for very distant elements.
     * <p></p>
     * Returns false if the position is not visible on screen
     *
     * @param worldPosition The 3D world position to render at
     * @param drawContext The draw context to use for matrix operations
     * @param scale Constant scale factor
     * @return true if position is visible and rendering started, false otherwise
     */
    public static boolean start(Vec3d worldPosition, DrawContext drawContext, double scale) {
        return Instances.getBillboard().start(worldPosition, drawContext, scale);
    }

    /**
     * Ends billboard rendering and restores matrix state
     * <p></p>
     * Must be called after start() to clean up rendering state
     *
     * @param drawContext The draw context to restore matrix state for
     */
    public static void stop(DrawContext drawContext) {
        Instances.getBillboard().stop(drawContext);
    }
}
