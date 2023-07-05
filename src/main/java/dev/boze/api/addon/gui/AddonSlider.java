package dev.boze.api.addon.gui;

/**
 * Slider gui element
 */
public interface AddonSlider extends AddonElement {

    /**
     * @return The slider's current value
     */
    double getValue();

    /**
     * Set the slider's value
     *
     * @param value The new value
     */
    void setValue(double value);

    /**
     * @return The slider's minimum value
     */
    double getMin();

    /**
     * @return The slider's maximum value
     */
    double getMax();

    /**
     * Set this to 1 if your setting is an integer
     *
     * @return The slider's step
     */
    double getStep();
}
