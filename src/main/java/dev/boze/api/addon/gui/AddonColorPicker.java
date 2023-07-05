package dev.boze.api.addon.gui;

import dev.boze.api.render.LinkedColor;

/**
 * Color picker gui element
 */
public class AddonColorPicker implements AddonElement {

    private final String name;
    private final String description;
    private final LinkedColor color;

    /**
     * Create a new color picker
     *
     * @param name        The name of the color picker
     * @param description The description of the color picker
     * @param color       The color picker's linked color
     */
    public AddonColorPicker(String name, String description, LinkedColor color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return The color picker's linked color
     *
     */
    public LinkedColor getColor() {
        return color;
    }
}
