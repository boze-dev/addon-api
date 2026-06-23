package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;
import java.util.function.BooleanSupplier;
import net.minecraft.util.Mth;

/**
 * A numeric option that can be adjusted using a slider
 * <br>
 * SliderOption provides a way to store and modify numeric values within a specified range
 * <br>
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
    public SliderOption(BaseModule owner, String name, String description, double value, double min, double max, double step) {
       super(owner, name, description);
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;

        this.defaultValue = value;
    }

    /**
     * Creates a new slider option with a parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param value The initial value
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param step The increment between values
     * @param parent The parent option, or null if this is a root option
     */
    public SliderOption(BaseModule owner, String name, String description, double value, double min, double max, double step, Option<?> parent) {
        super(owner, name, description, parent);
        this.value = value;
        this.min = min;
        this.max = max;
        this.step = step;
        this.defaultValue = value;
    }

    /**
     * Creates a new slider option with visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param value The initial value
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param step The increment between values
     * @param visibility The visibility supplier for this option
     */
    public SliderOption(BaseModule owner, String name, String description, double value, double min, double max, double step, BooleanSupplier visibility) {
        this(owner, name, description, value, min, max, step, visibility, null);
    }

    /**
     * Creates a new slider option with visibility and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param value The initial value
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param step The increment between values
     * @param visibility The visibility supplier for this option
     * @param parent The parent option, or null if this is a root option
     */
    public SliderOption(BaseModule owner, String name, String description, double value, double min, double max, double step, BooleanSupplier visibility, Option<?> parent) {
        super(owner, name, description, visibility, parent);
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
        this.value = Mth.clamp(newValue, min, max);
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
