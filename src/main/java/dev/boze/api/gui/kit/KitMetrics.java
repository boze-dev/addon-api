package dev.boze.api.gui.kit;

/**
 * Sizes the built-in editor layouts are built from.
 *
 * @param unit      Base spacing; layouts are expressed in multiples of it
 * @param textScale Text scale
 * @param iconScale Icon scale
 * @param rowHeight Height of one setting row
 * @param shadow    Whether text is drawn with a shadow
 */
public record KitMetrics(double unit, double textScale, double iconScale, double rowHeight, boolean shadow) {
}
