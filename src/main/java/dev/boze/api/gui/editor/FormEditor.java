package dev.boze.api.gui.editor;

import java.util.List;

/**
 * A few text fields and buttons, such as adding an account or editing a proxy.
 */
public non-sealed interface FormEditor extends GuiEditor {
    String prompt();

    List<InputControl> fields();

    List<EditorButton> buttons();

    /**
     * @return Size hint for built-in layouts, or null to fit the fields
     */
    default EditorSize size() {
        return null;
    }
}
