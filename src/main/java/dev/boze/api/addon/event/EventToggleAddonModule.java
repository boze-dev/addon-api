package dev.boze.api.addon.event;

import dev.boze.api.addon.module.ToggleableModule;

/**
 * Event called by addon ToggleableModules when toggled
 */
public class EventToggleAddonModule {
    private static final EventToggleAddonModule INSTANCE = new EventToggleAddonModule();

    /**
     * The module that was toggled
     */
    private ToggleableModule module;

    /**
     * The new state of the module
     */
    private boolean newState;

    /**
     * Gets the event instance - this is called when a ToggleableModule is toggled by Boze, and should not be called by addons
     *
     * @param module The module that was toggled
     * @param newState The new state of the module
     * @return The event instance
     */
    public static EventToggleAddonModule get(ToggleableModule module, boolean newState) {
        INSTANCE.module = module;
        INSTANCE.newState = newState;
        return INSTANCE;
    }

    /**
     * Gets the module that was toggled
     *
     * @return The module that was toggled
     */
    public ToggleableModule getModule() {
        return module;
    }

    /**
     * Gets the new state of the module
     *
     * @return The new state of the module
     */
    public boolean getNewState() {
        return newState;
    }
}
