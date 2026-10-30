package dev.boze.api.gui.editor;

import dev.boze.api.gui.view.GuiFrame;
import dev.boze.api.gui.view.GuiView;

/**
 * A view of one editor. Each frame the theme's {@link EditorLayout} measures the open editors,
 * places the ones it shows, and those are rendered.
 */
public interface EditorView extends GuiView {
    GuiEditor editor();

    /**
     * Works out {@link #width()} and {@link #height()} for this frame.
     *
     * @param maxWidth  Widest the editor may be
     * @param maxHeight Tallest the editor may be
     * @param fill      Whether to take all of that space instead of the editor's own size
     */
    void measure(GuiFrame frame, double maxWidth, double maxHeight, boolean fill);

    double width();

    double height();

    double x();

    double y();

    void setPosition(double x, double y);

    default boolean contains(double mouseX, double mouseY) {
        return mouseX >= x() && mouseX <= x() + width() && mouseY >= y() && mouseY <= y() + height();
    }
}
