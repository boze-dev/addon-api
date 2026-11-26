package dev.boze.api.gui.editor;

import java.util.List;

/**
 * Edits a gradient: a bar showing its colors, with a stop per color that can be dragged along it,
 * and the gradient's options.
 */
public non-sealed interface GradientEditor extends GuiEditor {
    /**
     * @return The stops in order along the bar, at least one
     */
    List<GradientStop> stops();

    /**
     * @return Index of the selected stop in {@link #stops()}, or -1 when none is. A stop is selected
     * by opening its color, and stays selected until the color picker closes in view of this editor,
     * or the stop is removed.
     */
    int selected();

    void select(int index);

    /**
     * Moves a stop along the bar. A stop moved past another changes places with it in {@link #stops()}.
     * The selected stop stays selected wherever it ends up.
     *
     * @param position 0 to 1
     * @return The stop's index after the move
     */
    int move(int index, double position);

    /**
     * Adds a stop in the color the gradient has there.
     *
     * @param position 0 to 1
     * @return The new stop's index
     */
    int add(double position);

    /**
     * @return Whether a stop can be removed; a gradient keeps two
     */
    boolean canRemove();

    /**
     * Removes a stop, if one can be removed. When it was the selected stop, no stop is selected
     * afterwards and its color picker closes.
     */
    void remove(int index);

    /**
     * Opens a color picker for the selected stop.
     */
    void editColor();

    /**
     * @return Whether colors between stops blend their hue, saturation and brightness instead of
     * their red, green and blue, as {@link dev.boze.api.gui.render.GuiPaint#hsb} paints do
     */
    boolean hsb();

    /**
     * @return The gradient's options
     */
    List<EditorControl> controls();

    List<EditorButton> buttons();
}
