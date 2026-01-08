package dev.boze.api.render;

import dev.boze.api.internal.Instances;
import dev.boze.api.option.ColorOption;

/**
 * HUD rendering API for drawing 2D shapes on the screen
 * <br>
 * Provides methods for rendering lines and quads in 2D screen space
 * <br>
 * All drawing operations must be wrapped between {@link #start()} and {@link #draw()} calls
 */
public class HudDrawer {
    
    /**
     * Starts a new HUD drawing session
     * <br>
     * Must be called before any drawing operations. Throws RuntimeException if already started
     */
    public static void start() {
        Instances.getHudRender().start();
    }

    /**
     * Renders the current drawing session to the screen
     * <br>
     * Throws RuntimeException if start() was not called first
     */
    public static void draw() {
        Instances.getHudRender().draw();
    }

    /**
     * Renders a line
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x1 Starting X coordinate
     * @param y1 Starting Y coordinate
     * @param x2 Ending X coordinate
     * @param y2 Ending Y coordinate
     */
    public static void line(ClientColor color, float opacity, double x1, double y1, double x2, double y2) {
        Instances.getHudRender().line(color, opacity, x1, y1, x2, y2);
    }

    /**
     * Renders a quad/filled rectangle
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of the quad
     * @param height Height of the quad
     */
    public static void quad(ClientColor color, float opacity, double x, double y, double width, double height) {
        Instances.getHudRender().quad(color, opacity, x, y, width, height);
    }

    /**
     * Renders a line using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Starting X coordinate
     * @param y1 Starting Y coordinate
     * @param x2 Ending X coordinate
     * @param y2 Ending Y coordinate
     */
    public static void line(ColorOption.Value colorOption, double x1, double y1, double x2, double y2) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        line(colorOption.color, opacity, x1, y1, x2, y2);
    }

    /**
     * Renders a quad/filled rectangle using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of the quad
     * @param height Height of the quad
     */
    public static void quad(ColorOption.Value colorOption, double x, double y, double width, double height) {
        quad(colorOption.color, colorOption.fillOpacity, x, y, width, height);
    }
}
