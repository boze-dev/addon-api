package dev.boze.api.gui.theme;

import dev.boze.api.gui.editor.EditorLayout;
import dev.boze.api.gui.editor.EditorView;
import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.gui.hud.HudEditor;
import dev.boze.api.gui.kit.WidgetKit;
import dev.boze.api.gui.view.GuiSession;
import dev.boze.api.gui.view.GuiView;

/**
 * A complete look and feel for Boze's GUI: the module GUI, the HUD editor, and every editor
 * (popup).
 * <br>
 * Editors the theme does not draw itself fall back to built-in layouts drawn with {@link #kit()},
 * so a theme can restyle every popup through its kit alone.
 */
public interface GuiTheme {
    /**
     * @return Unique id, {@code namespace:name}
     */
    String id();

    /**
     * @return Name shown in the theme selector
     */
    String name();

    /**
     * @return Short description
     */
    default String description() {
        return "";
    }

    /**
     * Creates the module GUI. Called each time the GUI opens.
     *
     * @param session The session the view belongs to
     * @return The view
     */
    GuiView createClickGui(GuiSession session);

    /**
     * Creates the HUD editor. HUD elements are drawn beneath the view and drag themselves; the view
     * draws what surrounds them and passes clicks to {@code hudEditor} where it wants them.
     *
     * @param session   The session the view belongs to
     * @param hudEditor The HUD elements
     * @return The view
     */
    GuiView createHudEditor(GuiSession session, HudEditor hudEditor);

    /**
     * @return The widgets built-in editor layouts are drawn with
     */
    WidgetKit kit();

    /**
     * Creates a view for an editor.
     *
     * @param editor The editor to show
     * @return A view, or null to use the built-in layout drawn with {@link #kit()}
     */
    default EditorView createEditorView(GuiEditor editor) {
        return null;
    }

    /**
     * @return How open editors are placed on screen
     */
    default EditorLayout editorLayout() {
        return EditorLayout.row(12);
    }

    /**
     * @return Seconds an editor takes to fade and grow in as it opens, and to fade out as it
     * closes. Editors take as long to glide to a new place when others open or close beside them,
     * and the built-in layouts animate their own changes, such as list rows coming and going, over
     * the same time. 0 shows, hides and moves everything at once.
     * <br>
     * A view fading out is still drawn after its {@link GuiView#closed()}, and takes no input.
     */
    default double editorTransition() {
        return 0;
    }

    /**
     * Declares the theme's options. Called once, when the theme is registered.
     *
     * @param options Where to add options
     */
    default void declareOptions(ThemeOptions options) {
    }

    /**
     * Called when the theme becomes the active theme.
     */
    default void onSelected() {
    }

    /**
     * Called when another theme becomes the active theme.
     */
    default void onDeselected() {
    }
}
