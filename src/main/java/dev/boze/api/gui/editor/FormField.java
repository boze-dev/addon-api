package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;

/**
 * A text field in a form made with {@link GuiEditors#form}. Read what was typed from
 * {@code field}.
 *
 * @param label       Shown next to the field, or empty to show the field alone
 * @param placeholder Shown while the field is empty
 * @param masked      Whether the text is hidden, as for passwords
 */
public record FormField(String label, TextFieldState field, String placeholder, boolean masked) {
    public FormField {
        if (label == null) label = "";
        if (placeholder == null) placeholder = "";
    }

    public FormField(String label, TextFieldState field, String placeholder) {
        this(label, field, placeholder, false);
    }
}
