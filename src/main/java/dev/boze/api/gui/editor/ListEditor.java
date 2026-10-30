package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;

import java.util.List;

/**
 * A list of entries with row actions, optional tabs, optional controls below the list, and
 * buttons. Used for friends, configs, presets, colors, accounts, proxies, fonts, sounds and more.
 */
public non-sealed interface ListEditor extends GuiEditor {
    /**
     * @return Current rows, rebuilt when the data changes
     */
    List<ListEntry> entries();

    /**
     * @return Tab names, empty without tabs
     */
    List<String> tabs();

    int selectedTab();

    void selectTab(int tab);

    /**
     * @return Controls shown between the list and the buttons
     */
    List<EditorControl> controls();

    /**
     * @return Text field shown left of the buttons, or null
     */
    TextFieldState input();

    String inputPlaceholder();

    List<EditorButton> buttons();

    /**
     * @return Whether the buttons sit together in the middle instead of spread across the row
     */
    boolean compactButtons();

    EditorSize size();
}
