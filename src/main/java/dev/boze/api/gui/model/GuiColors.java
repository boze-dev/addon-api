package dev.boze.api.gui.model;

import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.render.ClientColor;

import java.util.List;

/**
 * The user's named colors.
 */
public interface GuiColors {
    /**
     * @return Color names, sorted
     */
    List<String> names();

    /**
     * @return The color, or null
     */
    ClientColor get(String name);

    /**
     * @return The color manager editor
     */
    GuiEditor managerEditor();
}
