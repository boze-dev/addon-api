package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;

import java.util.List;

/**
 * Two lists: what can be added, filtered by a search, and what is added. Used for block, item,
 * entity, effect, particle and packet lists and Maker's lists.
 */
public non-sealed interface DualListEditor extends GuiEditor {
    TextFieldState search();

    /**
     * @return Entries that can be added, matching the search, best matches first
     */
    List<ListEntry> available();

    /**
     * @return Entries in the list, newest first
     */
    List<ListEntry> added();

    /**
     * @return Whether entries carry images
     */
    boolean hasImages();

    void add(ListEntry entry);

    void remove(ListEntry entry);

    /**
     * @return Whether added entries have an amount, edited in place
     */
    boolean hasAmounts();

    /**
     * @return Text of the entry's amount box: the amount, or what is being typed
     */
    String amount(ListEntry entry);

    /**
     * @return Key of the entry whose amount is being typed, or null
     */
    String editingAmount();

    void editAmount(ListEntry entry);

    /**
     * Applies a typed amount, if any.
     */
    void commitAmount();

    String addedLabel();

    String emptyAvailableText();

    String emptyAddedText();
}
