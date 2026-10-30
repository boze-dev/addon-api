package dev.boze.api.event;

/**
 * EventBind
 * <br>
 * Event fired when a key or mouse button is pressed, repeated, or released
 * <br>
 * This event allows addons to intercept and modify input binding behavior
 */
public class EventBind extends CancellableEvent {
    private static final EventBind INSTANCE = new EventBind();

    /**
     * The InputConstants key code or mouse button that was pressed/released
     */
    public int bind;

    /**
     * The action that occurred
     * <br>
     * InputConstants.RELEASE = 0 (The key or button was released)<br>
     * InputConstants.PRESS = 1 (The key or button was pressed)<br>
     * InputConstants.REPEAT = -1 (The key was held down until it repeated)
     */
    public int action;

    /**
     * Whether this bind represents a mouse button (true) or keyboard key (false)
     */
    public boolean isButton;

    /**
     * InputConstants.MOD_* bits of the modifier keys held during the action (only applicable for keyboard keys)
     */
    public int modifiers;

    /**
     * Creates a new EventBind instance
     *
     * @param bind The key code or mouse button
     * @param action The action that occurred (InputConstants.PRESS, RELEASE or REPEAT)
     * @param isButton Whether this is a mouse button binding
     * @param modifiers Modifier keys held (0 for mouse buttons)
     * @return The EventBind instance
     */
    public static EventBind get(int bind, int action, boolean isButton, int modifiers) {
        INSTANCE.setCancelled(false);
        INSTANCE.bind = bind;
        INSTANCE.action = action;
        INSTANCE.isButton = isButton;
        INSTANCE.modifiers = modifiers;
        return INSTANCE;
    }
}
