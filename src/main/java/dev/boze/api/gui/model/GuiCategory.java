package dev.boze.api.gui.model;

import dev.boze.api.addon.Addon;
import dev.boze.api.gui.render.GuiIcon;

import java.util.List;

/**
 * A group of modules.
 */
public interface GuiCategory {
    /**
     * @return Stable id: Boze category name, {@code addon:<addon id>}, or {@code addons}
     */
    String id();

    String label();

    /**
     * @return Category icon, or null
     */
    GuiIcon icon();

    CategoryKind kind();

    /**
     * @return The addon for {@link CategoryKind#ADDON}, otherwise null
     */
    Addon addon();

    /**
     * @return Every module in the category, in Boze's order
     */
    List<GuiModule> modules();

    /**
     * @return Whether any module of the category can be used in the current client mode
     */
    boolean isShown();
}
