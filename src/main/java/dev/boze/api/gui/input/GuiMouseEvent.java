package dev.boze.api.gui.input;

import org.lwjgl.glfw.GLFW;

/**
 * A mouse press or release in GUI-scaled pixels.
 *
 * @param button      GLFW mouse button
 * @param modifiers   GLFW modifier bits
 * @param doubleClick Whether this press completes a double click
 */
public record GuiMouseEvent(double x, double y, int button, int modifiers, boolean doubleClick) {
    public boolean isLeft() {
        return button == GLFW.GLFW_MOUSE_BUTTON_LEFT;
    }

    public boolean isRight() {
        return button == GLFW.GLFW_MOUSE_BUTTON_RIGHT;
    }

    public boolean isMiddle() {
        return button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE;
    }
}
