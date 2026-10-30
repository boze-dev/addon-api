package dev.boze.api.gui.view;

/**
 * What an open GUI session is showing.
 */
public enum GuiSurface {
    /**
     * The module GUI.
     */
    CLICK_GUI,

    /**
     * The HUD editor.
     */
    HUD_EDITOR,

    /**
     * Editors alone, such as the account manager opened from the multiplayer screen. The session
     * closes when the last editor closes.
     */
    EDITOR
}
