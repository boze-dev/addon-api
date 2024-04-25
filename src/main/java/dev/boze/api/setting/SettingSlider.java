package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.module.ToggleableModule;
import net.minecraft.util.math.MathHelper;

public class SettingSlider extends SettingBase<Double> {

    private double value;

    private final double defaultValue;

    public final double min;
    public final double max;

    public final double step;

    public SettingSlider(ToggleableModule owner, String name, String description, double value, double min, double max, double step) {
       super(owner, name, description);
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;

        this.defaultValue = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Double setValue(Double newValue) {
        this.value = MathHelper.clamp(newValue, min, max);
        return value;
    }

    @Override
    public Double reset() {
        value = defaultValue;
        return value;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("value", value);
        return object;
    }

    @Override
    public Double fromJson(JsonObject object) {
        value = object.get("value").getAsDouble();
        return value;
    }
}
