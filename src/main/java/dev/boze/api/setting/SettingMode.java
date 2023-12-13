package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.gui.AddonModePicker;
import dev.boze.api.config.Serializable;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SettingMode implements AddonModePicker, Serializable<SettingMode> {

    private final String name;
    private final String description;

    private final List<String> modes;
    private int mode;

    public SettingMode(String name, String description, List<String> modes) {
        this.name = name;
        this.description = description;
        this.modes = modes;
        this.mode = 0;
    }

    public SettingMode(String name, String description, List<String> modes, int mode) {
        this.name = name;
        this.description = description;
        this.modes = modes;
        this.mode = mode;
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
    public List<String> getModes() {
        return modes;
    }

    @Override
    public int getMode() {
        return mode;
    }

    @Override
    public void setMode(int mode) {
        this.mode = MathHelper.clamp(mode, 0, modes.size() - 1);
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("mode", mode);
        return object;
    }

    @Override
    public SettingMode fromJson(JsonObject object) {
        mode = object.get("mode").getAsInt();
        return this;
    }
}
