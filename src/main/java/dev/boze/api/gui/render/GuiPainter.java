package dev.boze.api.gui.render;

import net.minecraft.network.chat.Component;

/**
 * Draws one frame of the GUI in GUI-scaled pixels.
 * <br>
 * Later calls draw over earlier ones wherever they overlap. Drawing is batched behind the
 * scenes, and clips, opacity, transforms, fonts and text sizes can change between any two calls
 * without making it slower.
 */
public interface GuiPainter {
    void rect(double x, double y, double width, double height, GuiPaint paint);

    void rect(double x, double y, double width, double height, int argb);

    /**
     * @param radius  Corner radius, clamped to half the shorter side
     * @param corners Which corners are rounded, see {@link Corners}
     */
    void roundRect(double x, double y, double width, double height, double radius, int corners, GuiPaint paint);

    void roundRect(double x, double y, double width, double height, double radius, int corners, int argb);

    /**
     * A rectangle with a color per corner, for gradients in two directions such as a color picker's
     * square. For a gradient between two colors on any shape, fill it with
     * {@link GuiPaint#horizontal} or {@link GuiPaint#vertical}. Gradient client colors cannot be
     * used here.
     */
    void gradient(double x, double y, double width, double height, GuiPaint topLeft, GuiPaint topRight,
                  GuiPaint bottomRight, GuiPaint bottomLeft);

    /**
     * A filled circle whose bounding square starts at (x, y).
     */
    void circle(double x, double y, double diameter, GuiPaint paint);

    /**
     * A rectangle's outline, drawn inside its bounds.
     *
     * @param thickness Outline width; half the shorter side or more fills the rectangle
     */
    void strokeRect(double x, double y, double width, double height, double thickness, GuiPaint paint);

    /**
     * A rounded rectangle's outline, drawn inside its bounds. Rounded corners get arcs that follow
     * the outer edge.
     *
     * @param radius    Corner radius, clamped to half the shorter side
     * @param corners   Which corners are rounded, see {@link Corners}
     * @param thickness Outline width; half the shorter side or more fills the shape
     */
    void strokeRoundRect(double x, double y, double width, double height, double radius, int corners, double thickness,
                         GuiPaint paint);

    /**
     * A circle's outline, drawn inside the circle whose bounding square starts at (x, y).
     */
    void strokeCircle(double x, double y, double diameter, double thickness, GuiPaint paint);

    /**
     * A soft glow or shadow around a rounded rectangle, strongest at its edge and fading out
     * {@code reach} past it. Nothing is drawn inside the rectangle, so it suits see-through panels.
     * It is drawn in order like any shape, with smooth edges and no extra passes, so for a glow around
     * one panel it is cheaper and smoother than {@link #drawOutline}, which follows any shapes in the
     * outline mask.
     *
     * @param radius Corner radius, clamped to half the shorter side
     * @param reach  How far it reaches past the edge
     */
    void shadow(double x, double y, double width, double height, double radius, double reach, GuiPaint paint);

    /**
     * A one pixel line.
     */
    void line(double x1, double y1, double x2, double y2, GuiPaint paint);

    /**
     * Draws text with its top-left at (x, y) in the current font.
     *
     * @return The drawn width
     */
    double text(String text, double x, double y, double scale, GuiPaint paint, boolean shadow);

    double text(String text, double x, double y, double scale, int argb, boolean shadow);

    /**
     * Draws formatted text. Styles with their own color override {@code paint}.
     *
     * @return The drawn width
     */
    double text(Component text, double x, double y, double scale, GuiPaint paint, boolean shadow);

    double textWidth(String text, double scale, boolean shadow);

    double textHeight(double scale, boolean shadow);

    /**
     * @return How far below the top of a line of text its baseline is, in the current font
     * @see GuiFont#baseline
     */
    double textBaseline(double scale);

    /**
     * @return How far above the baseline capitals reach, in the current font
     * @see GuiFont#capHeight
     */
    double textCapHeight(double scale);

    void icon(GuiIcon icon, double x, double y, double scale, GuiPaint paint, boolean shadow);

    double iconWidth(GuiIcon icon, double scale);

    double iconHeight(double scale, boolean shadow);

    /**
     * Draws an image in a square of {@code size}. See {@link GuiImage#deferred()}.
     */
    default void image(GuiImage image, double x, double y, double size) {
        image(image, x, y, size, size);
    }

    /**
     * Draws an image in a rectangle. Textures stretch to fill it; items, sprites and vector images
     * keep their shape, as large as fits, centered in it. Vector images are white.
     */
    default void image(GuiImage image, double x, double y, double width, double height) {
        image(image, x, y, width, height, null);
    }

    /**
     * Draws an image in a rectangle, as {@link #image(GuiImage, double, double, double, double)}.
     *
     * @param paint What a vector image is filled with, or null for white. Other images ignore it.
     */
    default void image(GuiImage image, double x, double y, double width, double height, GuiPaint paint) {
        image(image, x, y, width, height, paint, false);
    }

    /**
     * Draws an image in a rectangle, as {@link #image(GuiImage, double, double, double, double)}.
     *
     * @param paint  What a vector image is filled with, or null for white. Other images ignore it.
     * @param shadow Whether a vector image casts the shadow text does. Other images ignore it.
     */
    void image(GuiImage image, double x, double y, double width, double height, GuiPaint paint, boolean shadow);

    /**
     * Draws an image in a rectangle, as {@link #image(GuiImage, double, double, double, double, GuiPaint, boolean)},
     * turned around the rectangle's center.
     *
     * @param degrees How far the image is turned, clockwise
     */
    void image(GuiImage image, double x, double y, double width, double height, GuiPaint paint, boolean shadow, double degrees);

    /**
     * Clips later drawing, outline masks included, to a rectangle intersected with the current
     * clip.
     */
    void pushClip(double x, double y, double width, double height);

    void popClip();

    /**
     * Multiplies the opacity of later drawing.
     */
    void pushAlpha(float alpha);

    void popAlpha();

    /**
     * @return The current opacity multiplier
     */
    float alpha();

    /**
     * Moves and scales later drawing: a point (x, y) lands at (translateX + x * scale,
     * translateY + y * scale) in the current transform.
     */
    void pushTransform(double translateX, double translateY, double scale);

    void popTransform();

    /**
     * Draws everything until the matching {@link #popLayer} as one image, blended in at
     * {@code alpha}, so shapes that overlap inside the layer fade together instead of showing
     * through each other. A layer costs a copy of the screen; where nothing overlaps,
     * {@link #pushAlpha} looks the same. Items and sprites ({@link GuiImage#deferred()}) are not
     * part of a layer. Layers nest.
     *
     * @param alpha Opacity of the layer, 0 to 1
     */
    void pushLayer(float alpha);

    void popLayer();

    /**
     * Blurs what is already on screen inside a rounded rectangle, like frosted glass behind a panel.
     * Later drawing goes on top, so draw the panel's fill after this, partly see-through, for the
     * blur to show. Each call costs a few full-screen passes: blur a handful of panels, not every
     * row.
     *
     * @param radius   Corner radius, clamped to half the shorter side
     * @param corners  Which corners are rounded, see {@link Corners}
     * @param strength How blurry, 1 to 8
     */
    void backdropBlur(double x, double y, double width, double height, double radius, int corners, int strength);

    /**
     * Blurs what is already on screen once, and shows it until the matching {@link #popBackdrop}
     * inside the shapes added to {@link #backdropMask()}, under everything drawn in between. For
     * several panels this costs one {@link #backdropBlur}, where a call per panel blurs the
     * screen once each. Panels inside see the screen as it was at the push, not each other.
     * <br>
     * Shapes can be added at any point before the pop, except that a layer, an outline or a
     * {@link #backdropBlur} inside draws everything queued so far: add the shapes under that
     * drawing before it. Backdrops do not nest; an inner push and pop do nothing.
     *
     * @param strength How blurry, 1 to 8
     */
    void pushBackdrop(int strength);

    /**
     * As {@link #pushBackdrop(int)}. With {@code soft}, the blur fades out past the edges of the
     * shapes as far as it spreads, instead of stopping at them, the way blurring the shapes
     * themselves looks. Use it to blur something already drawn, such as panels behind a popup:
     * push it after drawing them, add their shapes and pop it with nothing drawn in between.
     * Soft costs a second blur, of the mask.
     *
     * @param strength How blurry, 1 to 8
     */
    void pushBackdrop(int strength, boolean soft);

    /**
     * @return Where the backdrop started by {@link #pushBackdrop} shows
     */
    GuiMask backdropMask();

    void popBackdrop();

    /**
     * Makes text and icons drawn until the matching {@link #popTextGlow} glow in their own colors.
     * The glow can reach past clips. Text drawn with the vanilla font, and characters that font
     * draws for Boze's fonts, do not glow. Glows nest; the innermost applies.
     *
     * @param spread   How far the glow reaches, 1 to 6
     * @param strength How bright it is, 0 to 1. 0.5 is as bright as the HUD's text bloom at its
     *                 default brightness
     */
    void pushTextGlow(int spread, float strength);

    void popTextGlow();

    /**
     * @return Silhouettes the next {@link #drawOutline} draws around
     */
    GuiMask outlineMask();

    /**
     * Draws an outline around everything added to {@link #outlineMask()} since the last call, then
     * clears the mask.
     */
    void drawOutline(OutlineStyle style);

    /**
     * Sets the font later text uses. Each frame starts with {@link GuiFonts#gui()}.
     */
    void setFont(GuiFont font);

    GuiFont font();
}
