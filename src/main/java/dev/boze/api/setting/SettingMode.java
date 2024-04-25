package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.module.ToggleableModule;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SettingMode extends SettingBase<Integer> {

    private final List<String> modes;
    private int mode;

    private final int defaultMode;

    public SettingMode(ToggleableModule owner, String name, String description, List<String> modes) {
        this(owner, name, description, modes, 0);
    }

    public SettingMode(ToggleableModule owner, String name, String description, List<String> modes, int mode) {
        super(owner, name, description);
        this.modes = modes;
        this.mode = mode;

        this.defaultMode = mode;
    }

    public List<String> getModes() {
        return modes;
    }

    public String getModeName() {
        return modes.get(mode);
    }

    @Override
    public Integer getValue() {
        return mode;
    }

    @Override
    public Integer setValue(Integer newValue) {
        this.mode = MathHelper.clamp(newValue, 0, modes.size() - 1);
        return mode;
    }

    @Override
    public Integer reset() {
        mode = defaultMode;
        return mode;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("mode", mode);
        return object;
    }

    @Override
    public Integer fromJson(JsonObject object) {
        mode = object.get("mode").getAsInt();
        return mode;
    }
}
