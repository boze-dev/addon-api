package dev.boze.api.gui.model;

public non-sealed interface ToggleSetting extends GuiSetting {
    boolean get();

    void set(boolean value);
}
