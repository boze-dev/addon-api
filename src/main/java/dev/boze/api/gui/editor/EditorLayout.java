package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.Rect;
import dev.boze.api.gui.view.GuiFrame;

import java.util.List;
import java.util.function.DoubleSupplier;

/**
 * Sizes and places the open editors.
 */
@FunctionalInterface
public interface EditorLayout {
    /**
     * Measures the editors it shows, with {@link EditorView#measure}, and places them.
     *
     * @param views The open editors' views, oldest first
     * @param area  Where editors go: the whole screen, or the view's
     *              {@link dev.boze.api.gui.view.GuiView#editorArea()}
     * @return The views to show, in drawing order. The others are hidden and take no input until
     * a later frame shows them.
     */
    List<EditorView> place(GuiFrame frame, List<EditorView> views, Rect area);

    /**
     * Side by side in one row centered in the area, oldest left, each at its own size and centered
     * vertically.
     *
     * @param gap Space between editors
     */
    static EditorLayout row(double gap) {
        return row(() -> gap);
    }

    static EditorLayout row(DoubleSupplier gap) {
        return (frame, views, area) -> {
            if (views.isEmpty()) return views;
            double spacing = gap.getAsDouble();
            double total = spacing * (views.size() - 1);
            for (EditorView view : views) {
                view.measure(frame, area.width(), area.height(), false);
                total += view.width();
            }
            double x = area.x() + Math.max(0, area.width() * 0.5 - total * 0.5);
            for (EditorView view : views) {
                view.setPosition(x, area.y() + Math.max(0, area.height() * 0.5 - view.height() * 0.5));
                x += view.width() + spacing;
            }
            return views;
        };
    }

    /**
     * The newest editor fills the area; the ones it was opened from wait hidden under it and come
     * back as it closes.
     */
    static EditorLayout stack() {
        return (frame, views, area) -> {
            if (views.isEmpty()) return views;
            EditorView top = views.getLast();
            top.measure(frame, area.width(), area.height(), true);
            top.setPosition(area.x(), area.y());
            return List.of(top);
        };
    }
}
