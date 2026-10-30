package dev.boze.api.gui.editor;

/**
 * A control in an editor. Controls with the same {@link #row()} share one row.
 */
public sealed interface EditorControl permits SliderControl, ToggleControl, InputControl {
    int row();

    String label();
}
