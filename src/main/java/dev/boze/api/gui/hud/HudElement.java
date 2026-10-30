package dev.boze.api.gui.hud;

import dev.boze.api.gui.model.GuiModule;

/**
 * One HUD element, positioned in GUI-scaled pixels.
 */
public interface HudElement {
    GuiModule module();

    double x();

    double y();

    double width();

    double height();

    boolean isEnabled();

    boolean isDragging();
}
