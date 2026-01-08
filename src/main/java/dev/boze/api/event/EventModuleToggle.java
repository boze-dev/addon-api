package dev.boze.api.event;

import dev.boze.api.client.module.BaseModule;

/**
 * EventModuleToggle
 * <br>
 * Event fired when a module is toggled on or off
 */
public class EventModuleToggle {
    private static final EventModuleToggle INSTANCE = new EventModuleToggle();

    private BaseModule module;

    /**
     * Gets the event instance
     *
     * @return The event instance
     */
    public static EventModuleToggle get(BaseModule module) {
        INSTANCE.module = module;
        return INSTANCE;
    }

    /**
     * Gets the module that was toggled
     *
     * @return The module
     */
    public BaseModule getModule() {
        return module;
    }
}
