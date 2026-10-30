package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;
import dev.boze.api.utility.input.Bind;

import java.util.List;

/**
 * Edits a macro's commands and bind.
 */
public non-sealed interface MacroEditor extends GuiEditor {
    List<String> commands();

    /**
     * @return Index of the command being edited in place, or -1
     */
    int editingIndex();

    /**
     * @return The in-place edit field
     */
    TextFieldState editing();

    /**
     * @return The field for a new command
     */
    TextFieldState addField();

    void edit(int index);

    void commitEdit();

    void delete(int index);

    void moveUp(int index);

    void moveDown(int index);

    void addCommand();

    Bind bind();

    boolean isBinding();

    void startBinding();

    EditorButton closeButton();
}
