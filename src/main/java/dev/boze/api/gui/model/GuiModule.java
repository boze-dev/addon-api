package dev.boze.api.gui.model;

import dev.boze.api.addon.Addon;
import dev.boze.api.client.module.BaseModule;
import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.utility.input.Bind;

import java.util.List;

/**
 * A module as the GUI shows it.
 */
public interface GuiModule {
    /**
     * @return Module name, stable and used by commands
     */
    String id();

    /**
     * @return Display title
     */
    String label();

    String description();

    GuiCategory category();

    /**
     * @return The module through the addon API
     */
    BaseModule module();

    /**
     * @return The addon that added the module, or null for Boze's own modules
     */
    Addon addon();

    ModuleKind kind();

    boolean isEnabled();

    void setEnabled(boolean enabled);

    void toggle();

    Bind bind();

    void setBind(Bind bind);

    /**
     * @return Whether the module is only on while its bind is held
     */
    boolean isHoldBind();

    void setHoldBind(boolean hold);

    /**
     * @return Whether the module shows in the HUD's module list
     */
    boolean isListed();

    void setListed(boolean listed);

    /**
     * @return Whether toggling the module shows a notification
     */
    boolean isNotify();

    void setNotify(boolean notify);

    /**
     * @return Whether the module belongs in the GUI right now: allowed in the client mode and not
     * hidden by its own state
     */
    boolean isShown();

    /**
     * @return Whether the module's settings are expanded
     */
    boolean isExpanded();

    void setExpanded(boolean expanded);

    /**
     * @return Whether the module has saved presets to manage
     */
    boolean hasPresets();

    /**
     * @return The presets editor, or null when {@link #hasPresets()} is false
     */
    GuiEditor presetsEditor();

    /**
     * @return The bind and toggle options editor
     */
    GuiEditor tuneEditor();

    /**
     * @return The editor of an {@link ModuleKind#EDITOR} module, otherwise null
     */
    GuiEditor editor();

    /**
     * @return Every setting in display order, children right after their parents
     */
    List<GuiSetting> settings();

    /**
     * @return The page whose settings are listed, or null when the module's main settings are
     */
    PageSetting openPage();

    /**
     * Marks the description as read.
     */
    void markDescriptionSeen();
}
