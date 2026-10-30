package dev.boze.api.gui.util;

import dev.boze.api.gui.render.GuiFont;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Fitting text into a width, measured with a font or any function that measures text.
 */
public final class TextLayout {
    private static final String ELLIPSIS = "...";

    private TextLayout() {
    }

    /**
     * Like {@link #fit(String, double, ToDoubleFunction)}, measuring with {@code font}.
     */
    public static String fit(String text, double maxWidth, GuiFont font, double scale, boolean shadow) {
        return fit(text, maxWidth, measure(font, scale, shadow));
    }

    /**
     * Like {@link #fitTail(String, double, ToDoubleFunction)}, measuring with {@code font}.
     */
    public static String fitTail(String text, double maxWidth, GuiFont font, double scale, boolean shadow) {
        return fitTail(text, maxWidth, measure(font, scale, shadow));
    }

    /**
     * Like {@link #wrap(String, double, ToDoubleFunction)}, measuring with {@code font}.
     */
    public static List<String> wrap(String text, double maxWidth, GuiFont font, double scale, boolean shadow) {
        return wrap(text, maxWidth, measure(font, scale, shadow));
    }

    private static ToDoubleFunction<String> measure(GuiFont font, double scale, boolean shadow) {
        return text -> font.width(text, scale, shadow);
    }

    /**
     * @return {@code text}, or its longest start that fits followed by "..."
     */
    public static String fit(String text, double maxWidth, ToDoubleFunction<String> width) {
        if (width.applyAsDouble(text) <= maxWidth) return text;
        int end = text.length();
        while (end > 0 && width.applyAsDouble(text.substring(0, end).stripTrailing() + ELLIPSIS) > maxWidth) {
            end--;
        }
        return text.substring(0, end).stripTrailing() + ELLIPSIS;
    }

    /**
     * @return The longest end of {@code text} that fits, for fields whose caret is at the end
     */
    public static String fitTail(String text, double maxWidth, ToDoubleFunction<String> width) {
        int start = 0;
        while (start < text.length() && width.applyAsDouble(text.substring(start)) > maxWidth) {
            start++;
        }
        return text.substring(start);
    }

    /**
     * Splits text into lines that fit, breaking at spaces. A word longer than a line gets a line
     * of its own and is cut with "...".
     */
    public static List<String> wrap(String text, double maxWidth, ToDoubleFunction<String> width) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isBlank()) return lines;
        StringBuilder line = new StringBuilder();
        for (String word : text.strip().split("\\s+")) {
            String candidate = line.isEmpty() ? word : line + " " + word;
            if (line.isEmpty() || width.applyAsDouble(candidate) <= maxWidth) {
                line.setLength(0);
                line.append(candidate);
            } else {
                lines.add(fit(line.toString(), maxWidth, width));
                line.setLength(0);
                line.append(word);
            }
        }
        if (!line.isEmpty()) lines.add(fit(line.toString(), maxWidth, width));
        return lines;
    }
}
