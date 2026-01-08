package dev.boze.api.internal.interfaces;

import dev.boze.api.render.ClientColor;

/**
 * Internal HUD rendering interface
 * <br>
 * Provides low-level HUD rendering functionality
 */
public interface IHudRender {

    /**
     * Starts a new drawing session
     * <br>
     * Must be called before any drawing operations. Throws RuntimeException if already started
     */
    void start();

    /**
     * Renders the current drawing session
     * <br>
     * Throws RuntimeException if start() was not called first
     */
    void draw();

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
    void line(ClientColor color, float opacity, double x1, double y1, double x2, double y2);

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
    void quad(ClientColor color, float opacity, double x, double y, double width, double height);
}
