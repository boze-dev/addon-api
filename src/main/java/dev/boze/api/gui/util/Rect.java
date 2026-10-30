package dev.boze.api.gui.util;

/**
 * An axis-aligned rectangle in GUI-scaled pixels.
 */
public record Rect(double x, double y, double width, double height) {
    public boolean contains(double px, double py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    public double right() {
        return x + width;
    }

    public double bottom() {
        return y + height;
    }

    public double centerX() {
        return x + width * 0.5;
    }

    public double centerY() {
        return y + height * 0.5;
    }
}
