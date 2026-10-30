package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;

import java.util.List;

/**
 * A searchable single choice, such as an item or a potion. Clicking a row chooses it.
 */
public non-sealed interface PickerEditor extends GuiEditor {
    TextFieldState search();

    /**
     * @return Rows matching the search; while the search is empty the first row clears the choice
     */
    List<ListEntry> entries();

    boolean hasImages();

    void select(ListEntry entry);

    /**
     * @return Index of the row to scroll to when the editor opens
     */
    int initialRow();
}
