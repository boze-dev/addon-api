package dev.boze.api.gui.model;

import java.util.List;

/**
 * Everything the GUI lists: categories, modules and their settings, as Boze shows them.
 * <br>
 * Values are live. Visibility can change from frame to frame, so check
 * {@link GuiModule#isShown()} and {@link GuiSetting#isShown()} while drawing.
 */
public interface GuiModel {
    /**
     * @return Categories of the module GUI, in Boze's order, addon categories last
     */
    List<GuiCategory> categories();

    /**
     * @return Categories of the HUD editor (HUD elements and graphs)
     */
    List<GuiCategory> hudCategories();

    /**
     * @param id Module name
     * @return The module, or null
     */
    GuiModule module(String id);

    GuiClientMode clientMode();

    /**
     * Switches the client mode. Modules the new mode leaves out are turned off.
     */
    void setClientMode(GuiClientMode mode);

    /**
     * @return Increases whenever modules or categories are added, removed or regrouped. Views
     * that cache layout rebuild it when this changes.
     */
    int version();

    /**
     * @return The user's named colors
     */
    GuiColors colors();
}
