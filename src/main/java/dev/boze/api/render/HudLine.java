package dev.boze.api.render;

import java.util.List;

/**
 * A single line of a HUD, made up of one or more colored {@link HudText} segments rendered
 * left to right and separated by spaces. A {@code List<HudLine>} stacks these lines vertically.
 *
 * @param texts the colored segments that make up this line
 */
public record HudLine(List<HudText> texts) {

    /**
     * Creates a line from the given colored segments.
     *
     * @param texts the colored segments, drawn left to right separated by spaces
     * @return a new HudLine
     */
    public static HudLine of(HudText... texts) {
        return new HudLine(List.of(texts));
    }
}
