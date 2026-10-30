package dev.boze.api.gui.editor;

import java.util.List;

public non-sealed interface ConfirmEditor extends GuiEditor {
    List<String> lines();

    void confirm();

    void cancel();
}
