package dev.boze.api.gui.kit;

import dev.boze.api.gui.editor.EditorButton;
import dev.boze.api.gui.render.GuiIcon;
import dev.boze.api.gui.render.GuiPaint;
import dev.boze.api.gui.render.GuiPainter;
import dev.boze.api.gui.util.Rect;
import dev.boze.api.gui.util.TextFieldState;

/**
 * A theme's look for the pieces built-in editor layouts are made of. Layouts decide where pieces
 * go and what clicks do; the kit decides what each piece looks like, in each {@link WidgetState}.
 */
public interface WidgetKit {
    KitMetrics metrics();

    GuiPaint textPaint(TextRole role);

    /**
     * Fills a rectangle as the given kind of surface.
     */
    void surface(GuiPainter painter, Surface surface, Rect bounds, WidgetState state);

    /**
     * Finishes the panels drawn since the last call, for effects drawn over a whole panel such as
     * outlines. Built-in layouts call it at the end of each editor.
     */
    default void finishPanels(GuiPainter painter) {
    }

    default void icon(GuiPainter painter, GuiIcon icon, double x, double y, GuiPaint paint) {
        painter.icon(icon, x, y, metrics().iconScale(), paint == null ? textPaint(TextRole.NORMAL) : paint, metrics().shadow());
    }

    /**
     * A button with its label centered. By default a {@link Surface#BUTTON} with the label in the
     * normal text color, muted while disabled, whatever the role.
     */
    default void button(GuiPainter painter, Rect bounds, String label, EditorButton.Role role, WidgetState state) {
        surface(painter, Surface.BUTTON, bounds, state);
        KitMetrics metrics = metrics();
        double width = painter.textWidth(label, metrics.textScale(), metrics.shadow());
        double height = painter.textHeight(metrics.textScale(), metrics.shadow());
        painter.text(label, bounds.centerX() - width * 0.5, bounds.centerY() - height * 0.5, metrics.textScale(),
                textPaint(state.disabled() ? TextRole.MUTED : TextRole.NORMAL), metrics.shadow());
    }

    /**
     * A labelled slider showing its value, with step buttons on either side of the track.
     *
     * @param fraction Position of the value, 0 to 1
     */
    void slider(GuiPainter painter, Rect bounds, String label, String value, double fraction, WidgetState state);

    /**
     * Like {@link #slider}, with a rainbow track.
     *
     * @param hue 0 to 1
     */
    void hueSlider(GuiPainter painter, Rect bounds, String label, String value, double hue, WidgetState state);

    /**
     * @return Where a slider drawn in {@code bounds} takes clicks and drags along its track
     */
    Rect sliderTrack(Rect bounds);

    /**
     * @param direction -1 for the decrease button, 1 for the increase button
     * @return Where a slider drawn in {@code bounds} takes clicks on that step button
     */
    Rect sliderStep(GuiPainter painter, Rect bounds, int direction);

    /**
     * A labelled switch.
     */
    void toggle(GuiPainter painter, Rect bounds, String label, boolean on, WidgetState state);

    /**
     * A saturation and brightness square for one hue, with a marker at the chosen point.
     */
    void saturationBrightness(GuiPainter painter, Rect bounds, float hue, float saturation, float brightness, WidgetState state);

    /**
     * @return Where a square drawn in {@code bounds} takes clicks and drags
     */
    Rect saturationBrightnessArea(Rect bounds);

    /**
     * A text field: its box, its text clipped to the box, and a caret while focused.
     */
    default void textField(GuiPainter painter, Rect bounds, TextFieldState field, String placeholder, boolean masked, WidgetState state) {
        textField(painter, bounds, field, placeholder, masked, metrics().textScale(), state);
    }

    /**
     * Like {@link #textField(GuiPainter, Rect, TextFieldState, String, boolean, WidgetState)}, with
     * text at {@code textScale}.
     */
    void textField(GuiPainter painter, Rect bounds, TextFieldState field, String placeholder, boolean masked, double textScale,
                   WidgetState state);
}
