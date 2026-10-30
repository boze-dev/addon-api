package dev.boze.api.gui.view;

import dev.boze.api.gui.editor.EditorStack;
import dev.boze.api.gui.hud.HudEditor;
import dev.boze.api.gui.input.GuiInput;
import dev.boze.api.gui.model.GuiModel;
import dev.boze.api.gui.theme.GuiTheme;
import dev.boze.api.gui.theme.GuiThemeState;
import dev.boze.api.utility.input.Bind;

/**
 * An open GUI, from opening until the screen closes.
 */
public interface GuiSession {
    /**
     * @return What the session is showing
     */
    GuiSurface surface();

    /**
     * @return The theme drawing this session
     */
    GuiTheme theme();

    /**
     * @return Modules, categories and settings
     */
    GuiModel model();

    /**
     * @return The open editors
     */
    EditorStack editors();

    /**
     * @return Cursor, clipboard, bind capture and drags
     */
    GuiInput input();

    /**
     * @return The active theme's saved state
     */
    GuiThemeState state();

    /**
     * @return The HUD elements while the HUD editor is open, otherwise null
     */
    HudEditor hudEditor();

    /**
     * Asks the GUI to close. The view's {@link GuiView#closeRequested()} decides whether it
     * runs an exit animation first.
     */
    void close();

    /**
     * Closes the GUI now. Views call this when their exit animation finishes.
     */
    void finishClose();

    /**
     * @return Whether a close was requested and is still animating
     */
    boolean isClosing();

    /**
     * @return The bind that opens this GUI
     */
    Bind openBind();

    /**
     * Loads every config and preset code in the clipboard, like pasting them into the load
     * command.
     */
    void pasteConfigCodes();

    /**
     * Switches this session to the module GUI.
     */
    void showClickGui();

    /**
     * Switches this session to the HUD editor. Needs a world.
     */
    void showHudEditor();
}
