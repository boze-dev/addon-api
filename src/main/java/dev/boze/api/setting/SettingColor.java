package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.render.DrawColor;

public class SettingColor extends SettingBase<DrawColor> {

    private DrawColor value;

    private final DrawColor defaultValue;

    public SettingColor(ToggleableModule owner, String name, String description, DrawColor value) {
        super(owner, name, description);
        this.value = value;

        this.defaultValue = value.clone();
    }

    @Override
    public DrawColor getValue() {
        return value;
    }

    @Override
    public DrawColor setValue(DrawColor newValue) {
        this.value = newValue;
        return value;
    }

    @Override
    public DrawColor reset() {
        this.value = defaultValue.clone();
        return value;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();

        object.addProperty("r", value.getR());
        object.addProperty("g", value.getG());
        object.addProperty("b", value.getB());
        object.addProperty("a", value.getA());

        object.addProperty("speed", value.getSpeed());
        object.addProperty("hueOffset", value.getHueOffset());

        object.addProperty("gradientX", value.getGradientX());
        object.addProperty("gradientY", value.getGradientY());

        object.addProperty("minHue", value.getMinHue());
        object.addProperty("maxHue", value.getMaxHue());

        return object;
    }

    @Override
    public DrawColor fromJson(JsonObject object) {
        value.setR(object.get("r").getAsInt());
        value.setG(object.get("g").getAsInt());
        value.setB(object.get("b").getAsInt());
        value.setA(object.get("a").getAsInt());

        value.setSpeed(object.get("speed").getAsFloat());
        value.setHueOffset(object.get("hueOffset").getAsFloat());

        value.setGradientX(object.get("gradientX").getAsInt());
        value.setGradientY(object.get("gradientY").getAsInt());

        value.setMinHue(object.get("minHue").getAsFloat());
        value.setMaxHue(object.get("maxHue").getAsFloat());

        return value;
    }
}
