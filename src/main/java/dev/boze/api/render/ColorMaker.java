package dev.boze.api.render;

import dev.boze.api.internal.Instances;
import dev.boze.api.internal.interfaces.IColors;

/**
 * Helper class to initialize colors, and register colors in the user's colors
 * <br>
 * Use the name-less methods when you need a default color for an option.
 * <br>
 * Use the name-full methods when you want to register a color in the user's colors
 */
public final class ColorMaker {

    private ColorMaker() {
    }

    /**
     * Retrieves an existing color by name.
     *
     * @param name Color identifier
     * @return Color or {@code null} if not present
     */
    public static ClientColor get(String name) {
        return Instances.getColors().getColor(name);
    }

    /**
     * Creates a static color.
     * <br>
     * Pass the registry name you intend to register with {@link #register(String, ClientColor)}. For setting defaults,
     * use {@link #staticColor(int, int, int)} which automatically uses {@code _default}.
     */
    public static ClientColor staticColor(String name, int red, int green, int blue) {
        return Instances.getColors().staticColor(name, red, green, blue);
    }

    /**
     * Packs the components before delegating to {@link #staticColor(String, int, int, int)}.
     */
    public static ClientColor staticColor(String name, int packed) {
        return staticColor(name, (packed >> 16) & 0xFF, (packed >> 8) & 0xFF, packed & 0xFF);
    }

    /**
     * Creates a static color marked as {@code _default}.
     */
    public static ClientColor staticColor(int red, int green, int blue) {
        return Instances.getColors().staticColor("_default", red, green, blue);
    }

    /**
     * Convenience overload that accepts a packed RGB value and marks the color as {@code _default}.
     */
    public static ClientColor staticColor(int packed) {
        return Instances.getColors().staticColor("_default", (packed >> 16) & 0xFF, (packed >> 8) & 0xFF, packed & 0xFF);
    }

    /**
     * Creates a changing color bound to a named entry.
     * <br>
     * Pass the registry name you intend to register with {@link #register(String, ClientColor)}. For setting defaults,
     * use {@link #changingColor(boolean, boolean, float, int...)} which automatically uses {@code _default}.
     */
    public static ClientColor changingColor(String name, boolean hsb, boolean mirror, float speed, int... components) {
        return Instances.getColors().changingColor(name, hsb, mirror, speed, toPackedArray(components));
    }

    /**
     * Convenience overload that defaults mirror to false.
     */
    public static ClientColor changingColor(String name, boolean hsb, float speed, int... components) {
        return changingColor(name, hsb, false, speed, components);
    }

    /**
     * Convenience overload that defaults hsb and mirror to false, and speed to 0.2F.
     */
    public static ClientColor changingColor(String name, int... components) {
        return changingColor(name, false, false, 0.2F, components);
    }

    /**
     * Creates a changing color marked as {@code _default}.
     */
    public static ClientColor changingColor(boolean hsb, boolean mirror, float speed, int... components) {
        return Instances.getColors().changingColor("_default", hsb, mirror, speed, toPackedArray(components));
    }

    /**
     * Convenience overload returning a default changing color.
     */
    public static ClientColor changingColor(boolean hsb, float speed, int... components) {
        return changingColor(hsb, false, speed, components);
    }

    /**
     * Convenience overload returning a default changing color.
     */
    public static ClientColor changingColor(int... components) {
        return changingColor(false, false, 0.2F, components);
    }

    /**
     * Creates a gradient color.
     * <br>
     * Gradient defaults require a unique identifier. Use a name that starts with {@code _default_}
     * followed by a suffix of your choosing (for example {@code _default_exampleGradient}).
     * There is no name-less overload because gradients require unique names.
     */
    public static ClientColor gradientColor(String name, boolean hsb, boolean mirror, float angle, float scale, float motion, int... components) {
        return Instances.getColors().gradientColor(name, hsb, mirror, angle, scale, motion, toPackedArray(components));
    }

    /**
     * Convenience overload that defaults mirror to false.
     */
    public static ClientColor gradientColor(String name, boolean hsb, float angle, float scale, float motion, int... components) {
        return gradientColor(name, hsb, false, angle, scale, motion, components);
    }

    /**
     * Convenience overload that defaults hsb and mirror to false; angle and motion to 0F; and scale to 1F.
     */
    public static ClientColor gradientColor(String name, int... components) {
        return gradientColor(name, false, false, 0F, 1F, 0F, components);
    }

    /**
     * Registers the provided color under the supplied name so it appears inside the user color list.
     * <br>
     * Existing colors using the same name are replaced. Use this after creating a color with a name when you
     * want users to edit it inside the color manager. Option defaults created through the name-less helpers should not
     * be registered.
     * <br>
     * This shouldn't be spammed.
     */
    public static ClientColor register(String name, ClientColor color) {
        return Instances.getColors().registerColor(name, color);
    }

    /**
     * Packs RGB components into an integer (0xRRGGBB).
     */
    public static int pack(int red, int green, int blue) {
        return (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF);
    }

    private static int[] toPackedArray(int... components) {
        if (components == null || components.length == 0) {
            throw new IllegalArgumentException("At least one color must be provided");
        }
        if (components.length % 3 == 0) {
            int[] packed = new int[components.length / 3];
            for (int i = 0, j = 0; i < components.length; i += 3, j++) {
                packed[j] = pack(components[i], components[i + 1], components[i + 2]);
            }
            return packed;
        }
        int[] packed = new int[components.length];
        System.arraycopy(components, 0, packed, 0, components.length);
        return packed;
    }
}
