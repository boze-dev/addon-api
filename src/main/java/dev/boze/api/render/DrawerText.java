package dev.boze.api.render;

import net.minecraft.client.gui.DrawContext;

/**
 * DrawerText
 *
 * Draws text
 */
public interface DrawerText {

    /**
     * Start drawing
     *
     * @param size size of the text
     */
    void startDrawing(double size);

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
     * Draw text
     *
     * @param text text to render
     * @param x x position
     * @param y y position
     * @param color color of the text
     * @param shadow if the text should have a shadow
     * @return the width of the text
     */
    double draw(String text, double x, double y, DrawColor color, boolean shadow);

    /**
     * Draw text
     *
     * @param text text to render
     * @param x x position
     * @param y y position
     * @param color color of the text
     * @return the width of the text
     */
    default double draw(String text, double x, double y, DrawColor color) {
        return draw(text, x, y, color, false);
    }

    /**
     * Start sizing
     *
     * Lets you use the width and height methods, without rendering the text
     *
     * @param size size of the text
     */
    void startSizing(double size);

    /**
     * Stop sizing
     */
    void stopSizing();

    /**
     * Get the width of the text
     *
     * @param text text to get the width of
     * @param shadow if the text has a shadow
     * @return the width of the text
     */
    double getWidth(String text, boolean shadow);

    /**
     * Get the width of the text without a shadow
     *
     * @param text text to get the width of
     * @return the width of the text
     */
    default double getWidth(String text) {
        return getWidth(text, false);
    }

    /**
     * Get the height of the text
     *
     * @param shadow if the text has a shadow
     * @return the height of the text
     */
    double getHeight(boolean shadow);

    /**
     * Get the height of the text without a shadow
     *
     * @return the height of the text
     */
    default double getHeight() { return getHeight(false); }
}
