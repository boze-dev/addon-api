package dev.boze.api.gui.model;

import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.gui.render.GuiPaint;

/**
 * A choice of one of the user's colors, with opacity.
 */
public non-sealed interface ColorSetting extends GuiSetting {
    /**
     * @return The chosen color at full opacity
     */
    GuiPaint paint();

    /**
     * @return Name of the chosen color as the GUI shows it
     */
    String colorName();

    /**
     * @return Whether the setting uses its default color rather than one the user picked
     */
    boolean isDefault();

    float fillOpacity();

    float outlineOpacity();

    /**
     * @return Whether fill and outline share one opacity
     */
    boolean singleOpacity();

    /**
     * @return The color choice editor
     */
    GuiEditor editor();
}
