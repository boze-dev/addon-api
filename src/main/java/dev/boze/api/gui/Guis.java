package dev.boze.api.gui;

import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.gui.model.GuiModel;
import dev.boze.api.gui.view.GuiSession;
import dev.boze.api.internal.Instances;

/**
 * Opens the themed GUI and gives access to the open session.
 */
public final class Guis {
    private Guis() {
    }

    /**
     * Opens the module GUI with the active theme.
     */
    public static void openClickGui() {
        Instances.getGui().openClickGui();
    }

    /**
     * Opens the HUD editor with the active theme. Needs a world.
     */
    public static void openHudEditor() {
        Instances.getGui().openHudEditor();
    }

    /**
     * Opens an editor made with {@link dev.boze.api.gui.editor.GuiEditors}: on top of the open
     * GUI's editors, or on a screen of its own when the themed GUI is not open. That screen returns
     * to the screen before it when the editor closes. Call it on the render thread.
     *
     * @param editor The editor to open
     */
    public static void openEditor(GuiEditor editor) {
        Instances.getGui().openEditor(editor);
    }

    /**
     * @return Whether a themed GUI session is open
     */
    public static boolean isOpen() {
        return Instances.getGui().session() != null;
    }

    /**
     * @return The open session, or null
     */
    public static GuiSession session() {
        return Instances.getGui().session();
    }

    /**
     * @return The GUI's view of every module, category and setting
     */
    public static GuiModel model() {
        return Instances.getGui().model();
    }
}
