package dev.boze.api.gui.input;

/**
 * Follows the mouse from {@link GuiInput#beginDrag} until the button is released.
 */
public interface GuiDrag {
    /**
     * @param x Mouse x in GUI-scaled pixels
     * @param y Mouse y in GUI-scaled pixels
     */
    void moved(double x, double y);

    /**
     * Called once when the drag ends, also when the GUI closes during it.
     */
    default void released() {
    }
}
