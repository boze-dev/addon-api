package dev.boze.api.utility.input;

/**
 * Represents a keyboard or mouse button binding
 * <br>
 * A bind can be either a keyboard key or a mouse button, identified by its GLFW code
 * <br>
 * This class is used throughout Boze to handle input bindings for modules and other features
 */
public class Bind {
    /**
     * Whether this bind is a mouse button (true) or keyboard key (false)
     */
    private boolean isButton;

    /**
     * The GLFW code for this bind
     * <br>
     * For keyboard keys, this is a GLFW_KEY_* constant
     * <br>
     * For mouse buttons, this is a GLFW_MOUSE_BUTTON_* constant
     */
    private int bind;

    /**
     * Creates a new bind
     *
     * @param isButton Whether this bind is a mouse button (true) or keyboard key (false)
     * @param bind The GLFW code for the key or button
     */
    public Bind(boolean isButton, int bind) {
        this.isButton = isButton;
        this.bind = bind;
    }

    /**
     * Gets the GLFW code for this bind
     * <br>
     * For keyboard keys, this returns a GLFW_KEY_* constant
     * <br>
     * For mouse buttons, this returns a GLFW_MOUSE_BUTTON_* constant
     *
     * @return The GLFW code for this bind
     */
    public int getBind() {
        return bind;
    }

    /**
     * Checks if this bind is a mouse button
     *
     * @return true if this bind is a mouse button, false if it's a keyboard key
     */
    public boolean isButton() {
        return isButton;
    }
}
