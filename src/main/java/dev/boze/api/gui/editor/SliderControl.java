package dev.boze.api.gui.editor;

public non-sealed interface SliderControl extends EditorControl {
    double value();

    double min();

    double max();

    /**
     * @return Position of the value between min and max, 0 to 1
     */
    double fraction();

    /**
     * Sets the value a slider position points at, rounded the way this control rounds.
     */
    void slideTo(double value);

    /**
     * @param direction 1 or -1
     * @param large     Ten steps instead of one
     */
    void nudge(int direction, boolean large);

    void reset();

    String formatted();
}
