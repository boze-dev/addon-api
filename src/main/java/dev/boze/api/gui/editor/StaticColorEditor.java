package dev.boze.api.gui.editor;

import java.util.List;

/**
 * Edits one color with a saturation and brightness square, a hue slider and RGB sliders.
 */
public non-sealed interface StaticColorEditor extends GuiEditor {
    float hue();

    float saturation();

    float brightness();

    void setHue(double hue);

    void resetHue();

    void setSaturationBrightness(double saturation, double brightness);

    void resetSaturationBrightness();

    /**
     * @return Red, green and blue sliders
     */
    List<SliderControl> channels();

    List<EditorButton> buttons();
}
