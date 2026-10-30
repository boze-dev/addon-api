package dev.boze.api.gui.theme;

import dev.boze.api.client.module.BaseModule;
import dev.boze.api.option.Option;

/**
 * Collects a theme's options, such as its colors. They are listed with the theme selection, under
 * {@link #owner()}, in every Boze GUI, but only while the theme is the selected one. They are
 * saved with the Visuals profile whether or not the theme is selected, so switching themes keeps
 * each theme's settings.
 * <br>
 * Commands and saved profiles name options after the theme, from the name part of its id:
 * {@code Size} of {@code example:neon} is {@code Neon.Size}. Different themes can therefore use
 * the same names. Like Boze's settings, names have no spaces.
 * <br>
 * An option's own visibility still applies on top of that. Give it while declaring the options: a
 * visibility set after the theme is registered replaces the check for the selected theme.
 * <br>
 * Values are loaded from configs, which may come from other players: check numeric values
 * before using them as sizes or loop bounds.
 */
public interface ThemeOptions {
    /**
     * @return The module to pass as owner when constructing options
     */
    BaseModule owner();

    /**
     * Adds an option. Full names, parents included, must be unique within the theme.
     *
     * @param option The option
     * @param <T>    Option type
     * @return The same option
     */
    <T extends Option<?>> T add(T option);
}
