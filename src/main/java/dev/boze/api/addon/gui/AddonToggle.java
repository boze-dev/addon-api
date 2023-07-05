package dev.boze.api.addon.gui;

/**
 * Toggle gui element
 */
public interface AddonToggle extends AddonElement {

    /**
     * @return The toggle's value
     */
    boolean getValue();

    /**
     * Set the toggle's value
     *
     * @param value The new value
     */
    void setValue(boolean value);
}
