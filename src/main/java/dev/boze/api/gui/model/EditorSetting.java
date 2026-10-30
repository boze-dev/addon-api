package dev.boze.api.gui.model;

import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.gui.render.GuiIcon;

/**
 * A setting edited in an editor, such as a block list or a font.
 */
public non-sealed interface EditorSetting extends GuiSetting {
    /**
     * @return What the row is labelled with, for example "Manage Friends"
     */
    String rowLabel();

    /**
     * @return Short summary of the value: an entry count or the current choice, possibly empty
     */
    String summary();

    /**
     * @return Icon for the row
     */
    GuiIcon icon();

    /**
     * @return A new editor for the setting
     */
    GuiEditor editor();
}
