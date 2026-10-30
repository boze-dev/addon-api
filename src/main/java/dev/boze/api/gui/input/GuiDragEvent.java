package dev.boze.api.gui.input;

/**
 * Mouse movement with a button held, in GUI-scaled pixels.
 */
public record GuiDragEvent(double x, double y, int button, double deltaX, double deltaY) {
}
