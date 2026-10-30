package dev.boze.api.gui.editor;

import java.util.List;

/**
 * Typed number entry for one value or a range.
 */
public non-sealed interface NumberEditor extends GuiEditor {
    String prompt();

    List<InputControl> fields();

    List<EditorButton> buttons();
}
