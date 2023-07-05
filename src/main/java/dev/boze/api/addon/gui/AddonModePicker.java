package dev.boze.api.addon.gui;

import java.util.List;

/**
 * Mode picker gui element
 */
public interface AddonModePicker extends AddonElement {

    /**
     * @return List of modes
     */
    List<String> getModes();

    /**
     * @return The current mode
     */
    int getMode();

    /**
     * Set the mode
     *
     * @param mode The new mode
     */
    void setMode(int mode);
}
