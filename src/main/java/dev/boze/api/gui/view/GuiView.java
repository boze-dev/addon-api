package dev.boze.api.gui.view;

import dev.boze.api.gui.input.GuiCharEvent;
import dev.boze.api.gui.input.GuiDragEvent;
import dev.boze.api.gui.input.GuiKeyEvent;
import dev.boze.api.gui.input.GuiMouseEvent;
import dev.boze.api.gui.input.GuiScrollEvent;
import dev.boze.api.gui.util.Rect;

/**
 * Something that draws and takes input, in GUI-scaled pixels. Input handlers return whether
 * they used the event.
 */
public interface GuiView {
    /**
     * Draws one frame.
     *
     * @param frame The frame
     */
    void render(GuiFrame frame);

    default boolean mouseClicked(GuiMouseEvent event) {
        return false;
    }

    default boolean mouseReleased(GuiMouseEvent event) {
        return false;
    }

    default boolean mouseDragged(GuiDragEvent event) {
        return false;
    }

    default boolean mouseScrolled(GuiScrollEvent event) {
        return false;
    }

    default boolean keyPressed(GuiKeyEvent event) {
        return false;
    }

    default boolean keyReleased(GuiKeyEvent event) {
        return false;
    }

    default boolean charTyped(GuiCharEvent event) {
        return false;
    }

    /**
     * Called once before the first frame.
     */
    default void opened() {
    }

    /**
     * Called once when the view goes away.
     */
    default void closed() {
    }

    /**
     * Called when the window's GUI-scaled size changes.
     *
     * @param width  New width
     * @param height New height
     */
    default void resized(double width, double height) {
    }

    /**
     * Called when the GUI is asked to close.
     *
     * @return True to run an exit animation and call {@link GuiSession#finishClose()} when it is
     * done, false to close at once
     */
    default boolean closeRequested() {
        return false;
    }

    /**
     * @return Whether typing goes to this view, which keeps movement keys and binds from acting
     * while the GUI is open
     */
    default boolean capturesKeyboard() {
        return false;
    }

    /**
     * @return How opaque the view is right now, 0 to 1, for example while it fades in or out.
     * Open editors are drawn at this opacity too, so they fade with the view.
     */
    default float opacity() {
        return 1F;
    }

    /**
     * Where open editors go, read every frame. By default they float over the whole screen and
     * the view is out of reach until they close: a click outside every editor closes the newest.
     * <br>
     * With an area, editors are placed in it by the theme's {@link dev.boze.api.gui.editor.EditorLayout}
     * and the view keeps working around them. Clicks outside the editors reach the view, and
     * editors close only when they close themselves, on Escape, or when the view closes them.
     *
     * @return The area in GUI-scaled pixels, or null for the whole screen
     */
    default Rect editorArea() {
        return null;
    }
}
