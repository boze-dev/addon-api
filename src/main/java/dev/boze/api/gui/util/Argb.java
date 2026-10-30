package dev.boze.api.gui.util;

/**
 * Helpers for colors packed as ARGB ints.
 */
public final class Argb {
    private Argb() {
    }

    public static int alpha(int argb) {
        return argb >>> 24;
    }

    public static int red(int argb) {
        return argb >> 16 & 0xFF;
    }

    public static int green(int argb) {
        return argb >> 8 & 0xFF;
    }

    public static int blue(int argb) {
        return argb & 0xFF;
    }

    public static int of(int alpha, int red, int green, int blue) {
        return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    /**
     * @param alpha New alpha, 0 to 255
     */
    public static int withAlpha(int argb, int alpha) {
        return (alpha & 0xFF) << 24 | argb & 0xFFFFFF;
    }

    /**
     * Multiplies the alpha by {@code factor}, 0 to 1.
     */
    public static int fade(int argb, float factor) {
        return withAlpha(argb, Math.round(alpha(argb) * Math.clamp(factor, 0F, 1F)));
    }

    /**
     * Blends two colors channel by channel, including alpha.
     *
     * @param t 0 gives {@code from}, 1 gives {@code to}
     */
    public static int blend(int from, int to, float t) {
        t = Math.clamp(t, 0F, 1F);
        return of(Math.round(alpha(from) + (alpha(to) - alpha(from)) * t),
                Math.round(red(from) + (red(to) - red(from)) * t),
                Math.round(green(from) + (green(to) - green(from)) * t),
                Math.round(blue(from) + (blue(to) - blue(from)) * t));
    }
}
