package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.gui.AddonToggle;
import dev.boze.api.config.Serializable;

public class SettingToggle implements AddonToggle, Serializable<SettingToggle> {

    private final String name;
    private final String description;

    private boolean value;

    public SettingToggle(String name, String description) {
        this.name = name;
        this.description = description;

        this.value = false;
    }

    public SettingToggle(String name, String description, boolean value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("value", value);
        return object;
    }

    @Override
    public SettingToggle fromJson(JsonObject object) {
        value = object.get("value").getAsBoolean();
        return this;
    }
}
