package dev.boze.api.gui.util;

/**
 * Curves that shape animation progress, for animations that run on a clock rather than a
 * {@link Spring}.
 */
public final class Easing {
    private Easing() {
    }

    /**
     * @param t Progress, 0 to 1; clamped
     * @return {@code t} eased in and out
     */
    public static float smooth(double t) {
        double c = Math.clamp(t, 0.0, 1.0);
        return (float) (c * c * (3 - 2 * c));
    }
}
