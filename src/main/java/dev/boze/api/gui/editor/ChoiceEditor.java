package dev.boze.api.gui.editor;

import java.util.List;

/**
 * A short menu of choices, such as the kind of account or color to add.
 */
public non-sealed interface ChoiceEditor extends GuiEditor {
    List<EditorButton> options();

    /**
     * @return Size hint for built-in layouts, or null for a narrow column of buttons
     */
    default EditorSize size() {
        return null;
    }
}
