package dev.boze.api.render;

import net.minecraft.client.util.math.MatrixStack;

public interface Drawer2D {

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
    void quad(double x, double y, double width, double height, LinkedColor topLeft, LinkedColor topRight, LinkedColor bottomRight, LinkedColor bottomLeft);

    /**
     * Draw a quad
     *
     * @param color color
     */
    default void quad(double x, double y, double width, double height, LinkedColor color) {
        quad(x, y, width, height, color, color, color, color);
    }
}
