package dev.boze.api.gui.kit;

/**
 * How a piece of a built-in layout is being used this frame.
 *
 * @param key      Identity of the piece that stays the same from frame to frame, for animating it
 *                 (for example with {@link dev.boze.api.gui.util.Spring}), or null
 * @param hovered  The mouse is over it
 * @param pressed  It is hovered while the left mouse button is held
 * @param focused  It takes typing, or is being edited
 * @param selected It is the current choice, such as the open tab or the chosen row
 * @param disabled It cannot be used right now
 */
public record WidgetState(Object key, boolean hovered, boolean pressed, boolean focused, boolean selected, boolean disabled) {
    /**
     * A piece nothing is happening to.
     */
    public static final WidgetState IDLE = new WidgetState(null, false, false, false, false, false);

    public WidgetState withFocused(boolean focused) {
        return new WidgetState(key, hovered, pressed, focused, selected, disabled);
    }

    public WidgetState withSelected(boolean selected) {
        return new WidgetState(key, hovered, pressed, focused, selected, disabled);
    }

    public WidgetState withDisabled(boolean disabled) {
        return new WidgetState(key, hovered, pressed, focused, selected, disabled);
    }
}
