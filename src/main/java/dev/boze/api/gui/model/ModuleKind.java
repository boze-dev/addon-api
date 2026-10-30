package dev.boze.api.gui.model;

/**
 * How a module behaves in the GUI.
 */
public enum ModuleKind {
    /**
     * Can be turned on and off, and has settings.
     */
    NORMAL,

    /**
     * Only holds settings; it cannot be turned on, bound or tuned.
     */
    LIMITED,

    /**
     * Opens an editor instead of listing settings, for example the account manager.
     */
    EDITOR
}
