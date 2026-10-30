package dev.boze.api.gui.view;

import dev.boze.api.gui.render.GuiPainter;

/**
 * One frame of an open GUI.
 */
public interface GuiFrame {
    /**
     * @return What to draw with. Only valid during this frame.
     */
    GuiPainter painter();

    double mouseX();

    double mouseY();

    /**
     * @return GUI-scaled screen width
     */
    double width();

    /**
     * @return GUI-scaled screen height
     */
    double height();

    /**
     * @return Seconds since the previous frame, at most 0.1
     */
    double deltaSeconds();

    /**
     * @return {@link System#nanoTime()} at the start of this frame
     */
    long nanoTime();

    float partialTick();

    GuiSession session();
}
