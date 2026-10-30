package dev.boze.api.gui;

import dev.boze.api.gui.theme.GuiTheme;
import dev.boze.api.internal.Instances;

import java.util.List;

/**
 * Registry of GUI themes.
 * <br>
 * Register addon themes from {@link dev.boze.api.addon.Addon#initialize()}. Boze ships
 * {@code boze:classic}, which is used whenever the selected theme is not registered.
 */
public final class GuiThemes {
    private GuiThemes() {
    }

    /**
     * Registers a theme. Ids must be unique; a second theme with the same id is rejected.
     *
     * @param theme The theme to register
     * @throws IllegalArgumentException If a theme with the same id is already registered
     */
    public static void register(GuiTheme theme) {
        Instances.getGui().registerTheme(theme);
    }

    /**
     * @param id Theme id, for example {@code boze:classic}
     * @return The registered theme with this id, or null
     */
    public static GuiTheme get(String id) {
        return Instances.getGui().theme(id);
    }

    /**
     * @return Every registered theme, in registration order
     */
    public static List<GuiTheme> all() {
        return Instances.getGui().themes();
    }

    /**
     * @return The theme the GUI opens with
     */
    public static GuiTheme active() {
        return Instances.getGui().activeTheme();
    }

    /**
     * Selects the theme the GUI opens with. An open GUI switches at the start of the next frame.
     *
     * @param id Theme id
     */
    public static void select(String id) {
        Instances.getGui().selectTheme(id);
    }
}
