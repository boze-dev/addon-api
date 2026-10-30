package dev.boze.api.gui.editor;

import dev.boze.api.gui.input.GuiCharEvent;
import dev.boze.api.gui.input.GuiKeyEvent;

/**
 * An editor (popup): what it edits and what its controls do, without any drawing. Views draw an
 * editor and call into it; typing is handled here so every theme types the same way.
 * <br>
 * There is one sub-interface per kind of editor, so a view picks its layout with a switch:
 * {@code switch (editor) { case ListEditor list -> ...; case FormEditor form -> ...; }}. Addons
 * create editors with {@link GuiEditors}.
 */
public sealed interface GuiEditor permits ListEditor, DualListEditor, PickerEditor, NumberEditor, StaticColorEditor,
        ChoiceEditor, FormEditor, MacroEditor, TuneEditor, ConfirmEditor, NoticeEditor, BindCaptureEditor {
    /**
     * @return Stable id naming what is edited, for example {@code boze:block-list} or
     * {@code boze:presets}, so a theme can give one editor its own view
     */
    String id();

    String title();

    /**
     * Handles a key while this editor is on top: typing into its fields, Enter, Tab and paste.
     *
     * @return Whether the key was used
     */
    boolean keyPressed(GuiKeyEvent event);

    /**
     * @return Whether the character was used
     */
    boolean charTyped(GuiCharEvent event);

    /**
     * Removes this editor and every editor above it.
     */
    void close();
}
