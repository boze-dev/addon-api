package dev.boze.api.gui.input;

import com.mojang.blaze3d.platform.InputConstants;

/**
 * A mouse press or release in GUI-scaled pixels.
 *
 * @param button      {@link InputConstants} mouse button
 * @param modifiers   {@link InputConstants} modifier bits
 * @param doubleClick Whether this press completes a double click
 */
public record GuiMouseEvent(double x, double y, int button, int modifiers, boolean doubleClick) {
    public boolean isLeft() {
        return button == InputConstants.MOUSE_BUTTON_LEFT;
    }

    public boolean isRight() {
        return button == InputConstants.MOUSE_BUTTON_RIGHT;
    }

    public boolean isMiddle() {
        return button == InputConstants.MOUSE_BUTTON_MIDDLE;
    }
}
