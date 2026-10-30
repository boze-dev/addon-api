package dev.boze.api.gui.hud;

import dev.boze.api.gui.input.GuiMouseEvent;

import java.util.List;

/**
 * The HUD elements while the HUD editor is open. The HUD draws them beneath the editor's view,
 * and dragged elements follow the mouse on their own. A left click on an element starts dragging
 * it and a right click turns it off.
 */
public interface HudEditor {
    /**
     * @return Every HUD element, enabled or not
     */
    List<HudElement> elements();

    /**
     * Passes a press to the enabled element under the mouse.
     *
     * @return Whether an element took it
     */
    boolean mouseClicked(GuiMouseEvent event);

    /**
     * Ends any drag.
     */
    void mouseReleased(GuiMouseEvent event);

    /**
     * @return Space HUD elements keep from the screen edges, in GUI-scaled pixels
     */
    double margin();
}
