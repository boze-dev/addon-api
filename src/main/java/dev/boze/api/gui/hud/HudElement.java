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

    /**
     * @return Where along its width the element is held to the screen: 0 for its left edge, 0.5 for
     * its center and 1 for its right edge
     */
    default double anchorX() {
        return 0;
    }

    /**
     * @return Where along its height the element is held to the screen: 0 for its top edge, 0.5 for
     * its center and 1 for its bottom edge
     */
    default double anchorY() {
        return 0;
    }

    /**
     * @return The element's attachment to another element, or null if it has none. While dragging,
     * the attachment dropping it now would make.
     */
    default HudLink link() {
        return null;
    }
}
