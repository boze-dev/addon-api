package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.addon.AddonModule;

/**
 * A boolean option that can be toggled on or off
 * <p></p>
 * ToggleOption provides a simple way to store and modify boolean values
 * <p></p>
 * The value can only be true or false
 */
public class ToggleOption extends Option<Boolean> {

    private boolean value;

    private final boolean defaultValue;

    /**
     * Creates a new toggle option with a default value of false
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public ToggleOption(AddonModule owner, String name, String description) {
       this(owner, name, description, false);
    }

    /**
     * Creates a new toggle option with a specified default value
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param value The initial value
     */
    public ToggleOption(AddonModule owner, String name, String description, boolean value) {
        super(owner, name, description);
        this.value = value;

        this.defaultValue = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public Boolean setValue(Boolean newValue) {
        this.value = newValue;
        return value;
    }

    @Override
    public Boolean reset() {
        this.value = defaultValue;
        return value;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("value", value);
        return object;
    }

    @Override
    public Boolean fromJson(JsonObject object) {
        value = object.get("value").getAsBoolean();
        return value;
    }
}
