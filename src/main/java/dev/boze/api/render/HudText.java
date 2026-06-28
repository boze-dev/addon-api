package dev.boze.api.render;

/**
 * A single piece of colored text on a {@link HudLine}.
 * <br>
 * Multiple HudTexts on a line are rendered next to each other, separated by a space.
 *
 * @param text  the text to draw
 * @param color the color to draw it in (e.g. from {@link ColorMaker})
 */
public record HudText(String text, ClientColor color) {
}
