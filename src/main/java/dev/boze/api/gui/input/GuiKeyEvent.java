package dev.boze.api.gui.input;

import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.sdl.SDLKeyboard;

import java.util.Locale;

/**
 * A key press or release.
 *
 * @param key       {@link InputConstants} key code, the key's SDL scancode
 * @param keycode   SDL keycode, the key in the current keyboard layout
 * @param modifiers {@link InputConstants} modifier bits
 */
public record GuiKeyEvent(int key, int keycode, int modifiers) {
    private static final boolean MAC = System.getProperty("os.name", "").toLowerCase(Locale.ROOT).contains("mac");

    public boolean isEscape() {
        return key == InputConstants.KEY_ESCAPE;
    }

    public boolean isEnter() {
        return key == InputConstants.KEY_RETURN || key == InputConstants.KEY_NUMPADENTER;
    }

    public boolean isBackspace() {
        return key == InputConstants.KEY_BACKSPACE;
    }

    public boolean isDelete() {
        return key == InputConstants.KEY_DELETE;
    }

    public boolean isTab() {
        return key == InputConstants.KEY_TAB;
    }

    public boolean hasShift() {
        return (modifiers & InputConstants.MOD_SHIFT) != 0;
    }

    public boolean hasControl() {
        return (modifiers & InputConstants.MOD_CONTROL) != 0;
    }

    public boolean hasAlt() {
        return (modifiers & InputConstants.MOD_ALT) != 0;
    }

    /**
     * @return Whether Cmd (macOS) or Ctrl (elsewhere) is held
     */
    public boolean hasActionModifier() {
        return (modifiers & (MAC ? InputConstants.MOD_SUPER : InputConstants.MOD_CONTROL)) != 0;
    }

    public boolean isPaste() {
        return key == InputConstants.KEY_V && hasActionModifier();
    }

    public boolean isCopy() {
        return key == InputConstants.KEY_C && hasActionModifier();
    }

    public boolean isSelectAll() {
        return key == InputConstants.KEY_A && hasActionModifier();
    }

    /**
     * @return The printable name of the key in the current keyboard layout, or null
     */
    public String keyName() {
        String name = SDLKeyboard.SDL_GetKeyName(keycode);
        return name != null && name.codePointCount(0, name.length()) == 1 ? name : null;
    }
}
