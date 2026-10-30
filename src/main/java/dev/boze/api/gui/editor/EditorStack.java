package dev.boze.api.gui.editor;

import java.util.List;

/**
 * The open editors, oldest first. The newest one gets the keyboard; a click outside every editor
 * closes the newest.
 */
public interface EditorStack {
    List<GuiEditor> all();

    /**
     * @return The newest editor, or null
     */
    GuiEditor top();

    boolean isEmpty();

    /**
     * @return The view drawing {@code editor}
     */
    EditorView viewOf(GuiEditor editor);

    /**
     * Closes every editor, then opens this one.
     */
    void open(GuiEditor editor);

    /**
     * Opens an editor on top of the others.
     */
    void push(GuiEditor editor);

    /**
     * Closes every editor above {@code source}, then opens {@code editor} on top.
     */
    void pushAfter(GuiEditor source, GuiEditor editor);

    /**
     * Closes the newest editor.
     */
    void pop();

    /**
     * Closes the newest editor and opens this one in its place.
     */
    void replaceTop(GuiEditor editor);

    void closeAll();
}
