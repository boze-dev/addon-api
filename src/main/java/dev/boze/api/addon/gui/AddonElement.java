package dev.boze.api.addon.gui;

/**
 * Interface for gui element that can be added to a module
 *
 * Don't implement this directly, use one of the following:
 * @see AddonToggle
 * @see AddonSlider
 * @see AddonModePicker
 *
 */
public interface AddonElement {

    /**
     * @return The element's name
     */
    String getName();

    /**
     * @return The element's description
     */
    String getDescription();
}
