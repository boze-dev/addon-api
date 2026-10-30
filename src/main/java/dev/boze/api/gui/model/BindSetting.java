package dev.boze.api.gui.model;

import dev.boze.api.utility.input.Bind;

public non-sealed interface BindSetting extends GuiSetting {
    Bind get();

    void set(Bind bind);
}
