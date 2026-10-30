package dev.boze.api.gui.input;

/**
 * A scroll at a position in GUI-scaled pixels. Positive {@code vertical} scrolls up.
 */
public record GuiScrollEvent(double x, double y, double horizontal, double vertical) {
}
