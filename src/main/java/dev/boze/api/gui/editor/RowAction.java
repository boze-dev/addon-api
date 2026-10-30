package dev.boze.api.gui.editor;

import dev.boze.api.gui.render.GuiIcon;
import dev.boze.api.gui.render.GuiPaint;

/**
 * An icon button in a list row.
 *
 * @param paint Icon color, or null for the theme's text color
 */
public record RowAction(GuiIcon icon, GuiPaint paint, Runnable action) {
    public RowAction(GuiIcon icon, Runnable action) {
        this(icon, null, action);
    }

    public void run() {
        action.run();
    }
}
