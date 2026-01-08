package dev.boze.api.client.module;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.utility.config.Serializable;
import dev.boze.api.option.Option;

import java.util.List;

/**
 * ClientModuleExtension
 * <br>
 * Allows extending client modules with additional functionality, settings, and listeners
 * without modifying the core module code.
 * <br>
 * Extensions can define settings using Option fields, and override onEnable/onDisable
 * to add custom behavior. The extension will automatically subscribe/unsubscribe
 * to the event bus when enabled/disabled.
 */
public abstract class ClientModuleExtension implements Serializable<ClientModuleExtension> {

    /**
     * The parent client module this extension extends
     */
    public final ClientModule parent;

    /**
     * Creates a new client module extension
     *
     * @param parent The client module to extend
     */
    public ClientModuleExtension(ClientModule parent) {
        this.parent = parent;
    }

    /**
     * Called when the extension is enabled
     * <br>
     * This does NOT override the module's onEnable method, but allows you
     * to add additional behavior on top of the module's enable logic.
     * <br>
     * The extension is automatically subscribed to the event bus before this is called.
     */
    public void onEnable() {
        // Empty by default - override to add custom enable behavior
    }

    /**
     * Called when the extension is disabled
     * <br>
     * This does NOT override the module's onDisable method, but allows you
     * to add additional behavior on top of the module's disable logic.
     * <br>
     * The extension is automatically unsubscribed from the event bus after this is called.
     */
    public void onDisable() {
        // Empty by default - override to add custom disable behavior
    }

    /**
     * Called internally when the extension should be enabled
     * <br>
     * Subscribes to event bus and calls onEnable()
     */
    public final void enable() {
        BozeInstance.INSTANCE.subscribe(this);
        onEnable();
    }

    /**
     * Called internally when the extension should be disabled
     * <br>
     * Calls onDisable() and unsubscribes from event bus
     */
    public final void disable() {
        onDisable();
        BozeInstance.INSTANCE.unsubscribe(this);
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        // Get options from parent module
        List<Option<?>> options = parent.getExtensionOptions(this);
        for (Option<?> option : options) {
            object.add(option.name, option.toJson());
        }

        return object;
    }

    @Override
    public ClientModuleExtension fromJson(JsonObject object) {
        // Get options from parent module and deserialize
        List<Option<?>> options = parent.getExtensionOptions(this);
        for (Option<?> option : options) {
            if (object.has(option.name)) {
                option.fromJson(object.get(option.name).getAsJsonObject());
            }
        }

        return this;
    }
}
