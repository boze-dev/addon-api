package dev.boze.api.render;

import dev.boze.api.internal.Instances;
import dev.boze.api.option.ColorOption;
import net.minecraft.client.gui.GuiGraphicsExtractor;

/**
 * Text rendering API for drawing text in different contexts
 * <br>
 * Provides methods for rendering text in GUI, HUD, and world contexts
 * <br>
 * All rendering operations must be wrapped between {@link #start(TextType, double)} and {@link #draw(GuiGraphicsExtractor)} calls
 */
public class TextDrawer {

    /**
     * Starts a new text rendering session
     * <br>
     * Must be called before any text operations. Throws RuntimeException if already started
     *
     * @param type The text type (GUI/HUD/WORLD)
     * @param scale The text scale
     */
    public static void start(TextType type, double scale) {
        Instances.getTextRender().start(type, scale, false);
    }

    /**
     * Starts a new text rendering session with measure mode option
     * <br>
     * Measure mode disables buffer building for width/height calculations only
     * Must be called before any text operations. Throws RuntimeException if already started
     *
     * @param type The text type (GUI/HUD/WORLD)
     * @param scale The text scale
     * @param measureMode Whether to enable measure-only mode
     */
    public static void start(TextType type, double scale, boolean measureMode) {
        Instances.getTextRender().start(type, scale, measureMode);
    }

    /**
     * Renders the current text session to the specified draw context
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param context The draw context to render to
     */
    public static void draw(GuiGraphicsExtractor context) {
        Instances.getTextRender().draw(context);
    }

    /**
     * Renders the current text session to the default draw context
     * <br>
     * Throws RuntimeException if start() was not called first
     */
    public static void draw() {
        Instances.getTextRender().draw(null);
    }

    /**
     * Renders text at the specified position
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param text The text to render
     * @param x X coordinate
     * @param y Y coordinate
     * @param color The color to use
     * @param opacity The opacity value
     * @param shadow Whether to render with shadow
     * @return The width of the rendered text
     */
    public static double render(String text, double x, double y, ClientColor color, float opacity, boolean shadow) {
        return Instances.getTextRender().render(text, x, y, color, opacity, shadow);
    }

    /**
     * Renders text at the specified position without shadow
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param text The text to render
     * @param x X coordinate
     * @param y Y coordinate
     * @param color The color to use
     * @param opacity The opacity value
     * @return The width of the rendered text
     */
    public static double render(String text, double x, double y, ClientColor color, float opacity) {
        return Instances.getTextRender().render(text, x, y, color, opacity, false);
    }

    /**
     * Renders text at the specified position using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param text The text to render
     * @param x X coordinate
     * @param y Y coordinate
     * @param colorOption The color option value containing color settings
     * @param shadow Whether to render with shadow
     * @return The width of the rendered text
     */
    public static double render(String text, double x, double y, ColorOption.Value colorOption, boolean shadow) {
        return Instances.getTextRender().render(text, x, y, colorOption.color, colorOption.fillOpacity, shadow);
    }

    /**
     * Renders text at the specified position using ColorOption.Value without shadow
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param text The text to render
     * @param x X coordinate
     * @param y Y coordinate
     * @param colorOption The color option value containing color settings
     * @return The width of the rendered text
     */
    public static double render(String text, double x, double y, ColorOption.Value colorOption) {
        return Instances.getTextRender().render(text, x, y, colorOption.color, colorOption.fillOpacity, false);
    }

    /**
     * Gets the width of text with shadow option
     * <br>
     * Can be called anytime, does not require start()
     *
     * @param text The text to measure
     * @param shadow Whether shadow is enabled
     * @return The width of the text
     */
    public static double getWidth(String text, boolean shadow) {
        return Instances.getTextRender().getWidth(text, shadow);
    }

    /**
     * Gets the width of text without shadow
     * <br>
     * Can be called anytime, does not require start()
     *
     * @param text The text to measure
     * @return The width of the text
     */
    public static double getWidth(String text) {
        return Instances.getTextRender().getWidth(text, false);
    }

    /**
     * Gets the height of text with shadow option
     * <br>
     * Can be called anytime, does not require start()
     *
     * @param shadow Whether shadow is enabled
     * @return The height of the text
     */
    public static double getHeight(boolean shadow) {
        return Instances.getTextRender().getHeight(shadow);
    }

    /**
     * Gets the height of text without shadow
     * <br>
     * Can be called anytime, does not require start()
     *
     * @return The height of the text
     */
    public static double getHeight() {
        return Instances.getTextRender().getHeight(false);
    }
}
