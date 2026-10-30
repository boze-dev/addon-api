package dev.boze.api.gui.input;

import org.lwjgl.glfw.GLFW;

import java.util.Locale;

/**
 * A key press or release.
 *
 * @param key       GLFW key code
 * @param scancode  Platform scancode
 * @param modifiers GLFW modifier bits
 */
public record GuiKeyEvent(int key, int scancode, int modifiers) {
    private static final boolean MAC = System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("mac");

    public boolean isEscape() {
        return key == GLFW.GLFW_KEY_ESCAPE;
    }

    public boolean isEnter() {
        return key == GLFW.GLFW_KEY_ENTER || key == GLFW.GLFW_KEY_KP_ENTER;
    }

    public boolean isBackspace() {
        return key == GLFW.GLFW_KEY_BACKSPACE;
    }

    public boolean isDelete() {
        return key == GLFW.GLFW_KEY_DELETE;
    }

    public boolean isTab() {
        return key == GLFW.GLFW_KEY_TAB;
    }

    public boolean hasShift() {
        return (modifiers & GLFW.GLFW_MOD_SHIFT) != 0;
    }

    public boolean hasControl() {
        return (modifiers & GLFW.GLFW_MOD_CONTROL) != 0;
    }

    public boolean hasAlt() {
        return (modifiers & GLFW.GLFW_MOD_ALT) != 0;
    }

    /**
     * @return Whether Cmd (macOS) or Ctrl (elsewhere) is held
     */
    public boolean hasActionModifier() {
        return (modifiers & (MAC ? GLFW.GLFW_MOD_SUPER : GLFW.GLFW_MOD_CONTROL)) != 0;
    }

    public boolean isPaste() {
        return key == GLFW.GLFW_KEY_V && hasActionModifier();
    }

    public boolean isCopy() {
        return key == GLFW.GLFW_KEY_C && hasActionModifier();
    }

    public boolean isSelectAll() {
        return key == GLFW.GLFW_KEY_A && hasActionModifier();
    }

    /**
     * @return The printable name of the key in the current keyboard layout, or null
     */
    public String keyName() {
        return GLFW.glfwGetKeyName(key, scancode);
    }
}
