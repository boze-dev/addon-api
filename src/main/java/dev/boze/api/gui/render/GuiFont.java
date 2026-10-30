package dev.boze.api.gui.render;

/**
 * A font text can be drawn and measured with.
 */
public interface GuiFont {
    /**
     * Measures text. Works outside frames too.
     *
     * @param text   The text
     * @param scale  Text scale
     * @param shadow Whether the text is drawn with a shadow
     * @return Width in GUI-scaled pixels
     */
    double width(String text, double scale, boolean shadow);

    /**
     * @param scale  Text scale
     * @param shadow Whether the text is drawn with a shadow
     * @return Line height in GUI-scaled pixels
     */
    double height(double scale, boolean shadow);

    /**
     * @param scale Text scale
     * @return How far below the top of a line its baseline is, in GUI-scaled pixels
     */
    double baseline(double scale);

    /**
     * Together with {@link #baseline}, where capitals sit in a line, which is what text is centered
     * by to look centered.
     *
     * @param scale Text scale
     * @return How far above the baseline capitals reach, in GUI-scaled pixels
     */
    double capHeight(double scale);
}
