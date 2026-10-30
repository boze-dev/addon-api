package dev.boze.api.gui.model;

import dev.boze.api.gui.editor.GuiEditor;

/**
 * A number with a slider.
 */
public non-sealed interface NumberSetting extends GuiSetting {
    enum Kind {
        INT,
        FLOAT,
        DOUBLE
    }

    Kind kind();

    double get();

    /**
     * Sets the value, clamped to {@link #min()}..{@link #max()}.
     */
    void set(double value);

    double min();

    double max();

    double step();

    /**
     * @return Position of the value between min and max, 0 to 1
     */
    double fraction();

    /**
     * Sets the value a slider position points at, rounded the way this setting rounds.
     *
     * @param value Value under the slider, between min and max
     */
    void slideTo(double value);

    /**
     * Moves the value by one step, or ten when {@code large}.
     *
     * @param direction 1 or -1
     */
    void nudge(int direction, boolean large);

    /**
     * @return The value as the GUI shows it
     */
    String formatted();

    void reset();

    /**
     * @return An editor to type a value, which may lie outside the slider's range
     */
    GuiEditor entryEditor();
}
