package dev.boze.api.render;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;

/**
 * 3D Drawer interface (for 3D rendering)
 *
 */
public interface Drawer3D {

    /**
     * Start drawing
     */
    void startDrawing();

    /**
     * Stop drawing
     *
     * @param matrices MatrixStack to use for rendering, can be null
     */
    void stopDrawing(MatrixStack matrices);

    /**
     * Stop drawing
     *<p></p>
     * If you wish to render in the world, do not use this; instead, use the other stopDrawing method with matrices from EventWorldRender
     */
    default void stopDrawing() {
        stopDrawing(null);
    }

    /**
     * Draw a line
     *
     * @param x1 Origin x
     * @param y1 Origin y
     * @param z1 Origin z
     * @param x2 Destination x
     * @param y2 Destination y
     * @param z2 Destination z
     * @param color1 Origin color
     * @param color2 Destination color
     */
    void line(double x1, double y1, double z1, double x2, double y2, double z2, DrawColor color1, DrawColor color2);

    /**
     * Draw a line
     *
     * @param x1 Origin x
     * @param y1 Origin y
     * @param z1 Origin z
     * @param x2 Destination x
     * @param y2 Destination y
     * @param z2 Destination z
     * @param color Color (for both origin and destination)
     */
    default void line(double x1, double y1, double z1, double x2, double y2, double z2, DrawColor color) {
        line(x1, y1, z1, x2, y2, z2, color, color);
    }

    /**
     * Draw a quad
     *
     * @param topLeft Top left color
     * @param topRight Top right color
     * @param bottomRight Bottom right color
     * @param bottomLeft Bottom left color
     */
    void quad(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4, DrawColor topLeft, DrawColor topRight, DrawColor bottomRight, DrawColor bottomLeft);

    /**
     * Draw a quad
     *
     * @param color Color (for all corners)
     */
    default void quad(double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4, DrawColor color) {
        quad(x1, y1, z1, x2, y2, z2, x3, y3, z3, x4, y4, z4, color, color, color, color);
    }

    /**
     * Draw a box
     *
     * @param box Box to draw
     * @param color Color of the box
     */
    void box(Box box, DrawColor color);

    /**
     * Draw an outlined box
     *
     * @param box Box to draw
     * @param quadColor Color of the box's quads (sides)
     * @param lineColor Color of the box's lines
     */
    void outlinedBox(Box box, DrawColor quadColor, DrawColor lineColor);
}
