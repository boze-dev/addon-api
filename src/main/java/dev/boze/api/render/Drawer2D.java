package dev.boze.api.render;

import net.minecraft.client.gui.DrawContext;

public interface Drawer2D {

    /**
     * Start drawing
     */
    void startDrawing();

    /**
     * Stop drawing
     *
     * @param context DrawContext to use for rendering, can be null
     */
    void stopDrawing(DrawContext context);

    /**
     * Stop drawing
     */
    default void stopDrawing() {
        stopDrawing(null);
    }

    /**
     * Draw a quad
     *
     * @param topLeft top left color
     * @param topRight top right color
     * @param bottomRight bottom right color
     * @param bottomLeft bottom left color
     */
    void quad(double x, double y, double width, double height, DrawColor topLeft, DrawColor topRight, DrawColor bottomRight, DrawColor bottomLeft);

    /**
     * Draw a quad
     *
     * @param color color
     */
    default void quad(double x, double y, double width, double height, DrawColor color) {
        quad(x, y, width, height, color, color, color, color);
    }
}
