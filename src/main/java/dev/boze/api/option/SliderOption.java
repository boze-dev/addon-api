package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.addon.AddonModule;
import net.minecraft.util.math.MathHelper;

/**
 * A numeric option that can be adjusted using a slider
 * <p></p>
 * SliderOption provides a way to store and modify numeric values within a specified range
 * <p></p>
 * The value is always clamped between min and max, and can only be set in increments of step
 */
public class SliderOption extends Option<Double> {

    private double value;

    private final double defaultValue;

    /**
     * The minimum value this slider can be set to
     */
    public final double min;

    /**
     * The maximum value this slider can be set to
     */
    public final double max;

    /**
     * The increment by which this slider's value changes
     */
    public final double step;

    /**
     * Creates a new slider option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param value The initial value
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param step The increment between values
     */
    public SliderOption(AddonModule owner, String name, String description, double value, double min, double max, double step) {
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
