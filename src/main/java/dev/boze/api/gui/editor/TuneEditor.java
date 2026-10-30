package dev.boze.api.gui.editor;

import dev.boze.api.gui.model.GuiModule;

/**
 * A module's bind and toggle options.
 */
public non-sealed interface TuneEditor extends GuiEditor {
    GuiModule module();

    boolean isBinding();

    void startBinding();
}
