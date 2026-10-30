package dev.boze.api.gui.util;

import dev.boze.api.gui.input.GuiCursor;
import dev.boze.api.gui.input.GuiMouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Clickable areas recorded while drawing. Clicks are resolved against the areas of the last
 * finished frame, newest first, so only what was on screen can be clicked and something drawn on
 * top shields what lies under it.
 */
public final class Zones {
    private record Zone(Rect rect, Predicate<GuiMouseEvent> click, GuiCursor cursor) {
    }

    private List<Zone> zones = new ArrayList<>();
    private List<Zone> building = new ArrayList<>();

    /**
     * Starts recording a frame.
     */
    public void begin() {
        building = new ArrayList<>();
    }

    /**
     * Ends the frame; its areas take clicks until the next frame ends.
     */
    public void end() {
        zones = building;
    }

    /**
     * Records an area.
     *
     * @param click  Handles a press inside it; returns false to let the area beneath have it
     * @param cursor Cursor while hovering it, or null
     */
    public void add(Rect rect, Predicate<GuiMouseEvent> click, GuiCursor cursor) {
        if (rect.width() > 0 && rect.height() > 0) building.add(new Zone(rect, click, cursor));
    }

    /**
     * @return Whether an area took the press
     */
    public boolean click(GuiMouseEvent event) {
        for (int i = zones.size() - 1; i >= 0; i--) {
            Zone zone = zones.get(i);
            if (zone.rect().contains(event.x(), event.y()) && zone.click().test(event)) return true;
        }
        return false;
    }

    /**
     * @return Cursor of the topmost area under the point, or null
     */
    public GuiCursor cursorAt(double x, double y) {
        for (int i = zones.size() - 1; i >= 0; i--) {
            Zone zone = zones.get(i);
            if (zone.rect().contains(x, y) && zone.cursor() != null) return zone.cursor();
        }
        return null;
    }
}
