package dev.boze.api.gui.editor;

/**
 * Preferred size of a built-in editor layout, in the theme's unit
 * ({@link dev.boze.api.gui.kit.KitMetrics#unit()}). Layouts shrink it to fit the screen. Themes
 * with their own views can ignore it.
 */
public record EditorSize(double width, double height) {
}
