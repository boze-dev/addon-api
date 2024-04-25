package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.module.ToggleableModule;

public class SettingToggle extends SettingBase<Boolean> {

    private boolean value;

    private final boolean defaultValue;

    public SettingToggle(ToggleableModule owner, String name, String description) {
       this(owner, name, description, false);
    }

    public SettingToggle(ToggleableModule owner, String name, String description, boolean value) {
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
