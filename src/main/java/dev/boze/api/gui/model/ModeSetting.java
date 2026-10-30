package dev.boze.api.gui.model;

import java.util.List;

public non-sealed interface ModeSetting extends GuiSetting {
    List<String> choices();

    String selected();

    int selectedIndex();

    void select(int index);
}
