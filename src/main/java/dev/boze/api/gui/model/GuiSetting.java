package dev.boze.api.gui.model;

import dev.boze.api.option.Option;

import java.util.List;

/**
 * One setting row.
 */
public sealed interface GuiSetting permits ToggleSetting, NumberSetting, RangeSetting, ModeSetting, BindSetting,
        ColorSetting, GroupSetting, PageSetting, TextSetting, ActionSetting, EditorSetting {
    /**
     * @return Setting name, stable and used by commands
     */
    String id();

    /**
     * @return Display name
     */
    String label();

    String description();

    GuiModule module();

    /**
     * @return The setting this one is nested under, or null. Pages are not parents, see
     * {@link #page()}.
     */
    GuiSetting parent();

    /**
     * @return The page this setting is listed on, or null for the main list
     */
    PageSetting page();

    /**
     * @return Number of parents above this setting
     */
    int depth();

    /**
     * @return Settings nested directly under this one
     */
    List<GuiSetting> children();

    /**
     * @return Whether the setting is visible by its own conditions, its page and its parents'
     * conditions. Ignores whether parents are expanded.
     */
    boolean isVisible();

    /**
     * @return Whether the setting is visible and every parent is expanded
     */
    boolean isShown();

    /**
     * @return Whether the setting's children are expanded
     */
    boolean isExpanded();

    void setExpanded(boolean expanded);

    boolean hasVisibleChildren();

    /**
     * @return The addon API option behind this setting, or null
     */
    Option<?> option();

    /**
     * Marks the description as read.
     */
    void markDescriptionSeen();
}
