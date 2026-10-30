package dev.boze.api.gui.model;

public enum CategoryKind {
    /**
     * One of Boze's categories.
     */
    CLIENT,

    /**
     * An addon's own category.
     */
    ADDON,

    /**
     * Modules of addons without their own category.
     */
    ADDONS,

    /**
     * HUD elements.
     */
    HUD,

    /**
     * HUD graphs.
     */
    GRAPH
}
