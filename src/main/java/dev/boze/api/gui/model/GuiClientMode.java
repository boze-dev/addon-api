package dev.boze.api.gui.model;

/**
 * Boze's client mode, which decides which modules can be used.
 */
public enum GuiClientMode {
    GHOST,
    ANARCHY,
    BOTH;

    /**
     * @return Display name
     */
    public String label() {
        return switch (this) {
            case GHOST -> "Ghost";
            case ANARCHY -> "Anarchy";
            case BOTH -> "Both";
        };
    }
}
