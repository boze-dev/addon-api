package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.gui.AddonSlider;
import dev.boze.api.config.Serializable;
import net.minecraft.util.math.MathHelper;

public class SettingSlider implements AddonSlider, Serializable<SettingSlider> {

    private final String name;
    private final String description;

    private double value;

    private final double min;
    private final double max;

    private final double step;

    public SettingSlider(String name, String description, double value, double min, double max, double step) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
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
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = MathHelper.clamp(value, min, max);
    }

    @Override
    public double getMin() {
        return min;
    }

    @Override
    public double getMax() {
        return max;
    }

    @Override
    public double getStep() {
        return step;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("value", value);
        return object;
    }

    @Override
    public SettingSlider fromJson(JsonObject object) {
        value = object.get("value").getAsDouble();
        return this;
    }
}
