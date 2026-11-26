package dev.boze.api.gui.editor;

/**
 * A color on a gradient's bar.
 *
 * @param key      Identity of the stop, the same while it moves, changes place with others or changes color,
 *                 for animating it
 * @param position Where it sits along the bar, 0 to 1
 * @param rgb      Its color, without alpha
 */
public record GradientStop(Object key, double position, int rgb) {
}
