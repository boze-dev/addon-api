package dev.boze.api.gui.render;

import dev.boze.api.internal.Instances;
import dev.boze.api.option.ColorOption;
import dev.boze.api.render.ClientColor;

/**
 * What a shape or text is filled with: a plain ARGB color, an HSB color, one of the user's client
 * colors at some opacity, or a gradient between two of those. Changing and gradient client colors
 * keep animating when drawn.
 */
public interface GuiPaint {
    /**
     * @param argb Color with alpha in the top byte
     * @return The paint
     */
    static GuiPaint argb(int argb) {
        return Instances.getGui().argbPaint(argb);
    }

    /**
     * @param rgb   Color without alpha
     * @param alpha Opacity, 0 to 1
     * @return The paint
     */
    static GuiPaint rgb(int rgb, float alpha) {
        return Instances.getGui().argbPaint(Math.round(Math.clamp(alpha, 0F, 1F) * 255) << 24 | rgb & 0xFFFFFF);
    }

    /**
     * An HSB color. Gradients between HSB paints blend hue, saturation and brightness instead of
     * red, green and blue, which is what color pickers need.
     *
     * @param hue        0 to 1
     * @param saturation 0 to 1
     * @param brightness 0 to 1
     * @param alpha      0 to 1
     * @return The paint
     */
    static GuiPaint hsb(float hue, float saturation, float brightness, float alpha) {
        return Instances.getGui().hsbPaint(hue, saturation, brightness, alpha);
    }

    /**
     * @param color A client color
     * @param alpha Opacity, 0 to 1
     * @return The paint
     */
    static GuiPaint of(ClientColor color, float alpha) {
        return Instances.getGui().colorPaint(color, alpha);
    }

    /**
     * @param value A color option's value, drawn at its fill opacity
     * @return The paint
     */
    static GuiPaint of(ColorOption.Value value) {
        return of(value.color, value.fillOpacity);
    }

    /**
     * A gradient from {@code start} at the left edge of whatever it fills to {@code end} at the
     * right edge. See {@link #vertical} for how gradients behave.
     *
     * @return The paint
     */
    static GuiPaint horizontal(GuiPaint start, GuiPaint end) {
        return Instances.getGui().linearPaint(start, end, true);
    }

    /**
     * A gradient from {@code start} at the top edge of whatever it fills to {@code end} at the
     * bottom edge.
     * <br>
     * Shapes and outlines are shaded across their own bounds; text, icons and lines use
     * {@code start}. The ends blend in {@code start}'s color space, so between HSB colors hue,
     * saturation and brightness are blended. When either end is a client gradient color, the fill
     * uses {@code start} alone. {@link #alpha()} and {@link #argb()} describe {@code start}, and
     * {@link #withAlpha} gives both ends that opacity.
     *
     * @return The paint
     */
    static GuiPaint vertical(GuiPaint start, GuiPaint end) {
        return Instances.getGui().linearPaint(start, end, false);
    }

    /**
     * @return Opacity, 0 to 1
     */
    float alpha();

    /**
     * @param alpha New opacity, 0 to 1
     * @return A copy with this opacity
     */
    GuiPaint withAlpha(float alpha);

    /**
     * @return The color as ARGB right now. Changing colors are sampled at the current time;
     * gradients give their first color.
     */
    int argb();
}
