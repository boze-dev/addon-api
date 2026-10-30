package dev.boze.api.gui.model;

import dev.boze.api.gui.editor.GuiEditor;

/**
 * Two numbers on one slider.
 */
public non-sealed interface RangeSetting extends GuiSetting {
    NumberSetting.Kind kind();

    /**
     * @param index 0 or 1
     * @return That handle's value; handles may cross
     */
    double value(int index);

    /**
     * Sets one handle, clamped to {@link #min()}..{@link #max()}.
     */
    void set(int index, double value);

    /**
     * @return Index of the lower handle
     */
    int lowIndex();

    /**
     * @return Index of the higher handle
     */
    int highIndex();

    double min();

    double max();

    double step();

    /**
     * Rounds a slider value the way this setting rounds.
     */
    double round(double value);

    /**
     * Moves both handles by one step.
     *
     * @param direction 1 or -1
     */
    void stepBoth(int direction);

    /**
     * @return The range as the GUI shows it
     */
    String formatted();

    void reset();

    /**
     * @return An editor to type both values
     */
    GuiEditor entryEditor();
}
