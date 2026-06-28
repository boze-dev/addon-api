package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;
import java.util.function.BooleanSupplier;
import net.minecraft.util.Mth;

/**
 * A numeric option with a low and high value, adjusted using a two-handle range slider.
 * <br>
 * Both values are clamped between min and max and snapped to step. Use {@link #getMin()} and
 * {@link #getMax()} to read the lower/higher of the two regardless of which handle is which.
 */
public class RangeSliderOption extends Option<double[]> {

    private final double[] value;
    private final double[] defaultValue;

    /**
     * The minimum value either handle can be set to
     */
    public final double min;

    /**
     * The maximum value either handle can be set to
     */
    public final double max;

    /**
     * The increment by which the handles change
     */
    public final double step;

    /**
     * Creates a new range slider option.
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param low The initial low value
     * @param high The initial high value
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @param step The increment between values
     */
    public RangeSliderOption(BaseModule owner, String name, String description, double low, double high, double min, double max, double step) {
        super(owner, name, description);
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = new double[]{Mth.clamp(low, min, max), Mth.clamp(high, min, max)};
        this.defaultValue = value.clone();
    }

    /**
     * Creates a new range slider option with a parent.
     */
    public RangeSliderOption(BaseModule owner, String name, String description, double low, double high, double min, double max, double step, Option<?> parent) {
        super(owner, name, description, parent);
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = new double[]{Mth.clamp(low, min, max), Mth.clamp(high, min, max)};
        this.defaultValue = value.clone();
    }

    /**
     * Creates a new range slider option with visibility.
     */
    public RangeSliderOption(BaseModule owner, String name, String description, double low, double high, double min, double max, double step, BooleanSupplier visibility) {
        this(owner, name, description, low, high, min, max, step, visibility, null);
    }

    /**
     * Creates a new range slider option with visibility and a parent.
     */
    public RangeSliderOption(BaseModule owner, String name, String description, double low, double high, double min, double max, double step, BooleanSupplier visibility, Option<?> parent) {
        super(owner, name, description, visibility, parent);
        this.min = min;
        this.max = max;
        this.step = step;
        this.value = new double[]{Mth.clamp(low, min, max), Mth.clamp(high, min, max)};
        this.defaultValue = value.clone();
    }

    @Override
    public double[] getValue() {
        return value;
    }

    @Override
    public double[] setValue(double[] newValue) {
        set(0, newValue[0]);
        set(1, newValue[1]);
        return value;
    }

    /**
     * Sets one handle's value, clamped to [min, max].
     *
     * @param index 0 or 1
     * @param newValue The new value
     * @return The clamped value
     */
    public double set(int index, double newValue) {
        return value[index] = Mth.clamp(newValue, min, max);
    }

    /**
     * @return the index (0 or 1) of the lower handle
     */
    public int minIndex() {
        return value[0] <= value[1] ? 0 : 1;
    }

    /**
     * @return the index (0 or 1) of the higher handle
     */
    public int maxIndex() {
        return value[0] > value[1] ? 0 : 1;
    }

    /**
     * @return the lower of the two values
     */
    public double getMin() {
        return value[minIndex()];
    }

    /**
     * @return the higher of the two values
     */
    public double getMax() {
        return value[maxIndex()];
    }

    @Override
    public double[] reset() {
        value[0] = defaultValue[0];
        value[1] = defaultValue[1];
        return value;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("value0", value[0]);
        object.addProperty("value1", value[1]);
        return object;
    }

    @Override
    public double[] fromJson(JsonObject object) {
        value[0] = object.get("value0").getAsDouble();
        value[1] = object.get("value1").getAsDouble();
        return value;
    }
}
