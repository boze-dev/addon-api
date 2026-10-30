package dev.boze.api.gui.kit;

/**
 * Kinds of filled areas in built-in editor layouts. How each is being used comes with it as a
 * {@link WidgetState}.
 */
public enum Surface {
    /**
     * An editor's background.
     */
    PANEL,

    /**
     * Behind a list of rows.
     */
    LIST_AREA,

    /**
     * A list row: selected when it is the current choice, focused while it is being edited.
     */
    ROW,

    /**
     * A text field's box.
     */
    FIELD,

    /**
     * A small box, such as a search field or a header label.
     */
    BOX,

    /**
     * A button's background, drawn by {@link WidgetKit#button} unless the kit draws buttons
     * itself.
     */
    BUTTON,

    /**
     * A tab: selected when it is the open tab.
     */
    TAB,

    /**
     * A bind or value chip.
     */
    CHIP,

    /**
     * An amount box in a list row: focused while it is being typed in.
     */
    AMOUNT,

    SCROLL_THUMB,

    /**
     * A message card.
     */
    CARD,

    /**
     * The header strip of a message card.
     */
    CARD_HEADER,

    /**
     * The close button on a message card's header.
     */
    CARD_BUTTON,

    /**
     * Dims everything behind a message card.
     */
    BACKDROP
}
