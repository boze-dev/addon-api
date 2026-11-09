package dev.boze.api.gui.hud;

/**
 * A line showing what the dragged element snapped to, in GUI-scaled pixels.
 */
public record HudGuide(double x1, double y1, double x2, double y2) {
}
