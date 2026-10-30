package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;

/**
 * A text field. Its editor decides which characters it takes.
 */
public non-sealed interface InputControl extends EditorControl {
    TextFieldState field();

    String placeholder();

    /**
     * @return Whether the text is hidden, as for passwords
     */
    boolean masked();

    boolean isFocused();

    void focus();
}
