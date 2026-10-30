package dev.boze.api.gui.model;

/**
 * A page of settings. While a page is open, the module lists only that page.
 */
public non-sealed interface PageSetting extends GuiSetting {
    boolean isOpen();

    void open();

    void close();
}
