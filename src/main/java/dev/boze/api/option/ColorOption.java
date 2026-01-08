package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;
import dev.boze.api.render.ClientColor;
import dev.boze.api.render.ClientColorBinding;
import dev.boze.api.render.ColorMaker;

import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * An option for selecting and configuring colors
 * <br>
 * ColorOption provides a way to store and modify color values with associated opacity settings
 */
public class ColorOption extends Option<ColorOption.Value> implements ClientColorBinding {

    private Value value;
    private final Value defaultValue;
    private final boolean singleOpacity;

    /**
     * Creates a new color option with single opacity
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value used
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity) {
        this(owner, name, description, defaultColor, fillOpacity, fillOpacity, true, () -> true, null);
    }

    /**
     * Creates a new color option with single opacity and a parent option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value used
     * @param parent The parent option, or null if this is a root option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, Option<?> parent) {
        this(owner, name, description, defaultColor, fillOpacity, fillOpacity, true, () -> true, parent);
    }

    /**
     * Creates a new color option with separate fill and outline opacity
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value for fill
     * @param outlineOpacity The opacity value for outline
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity) {
        this(owner, name, description, defaultColor, fillOpacity, outlineOpacity, false, () -> true, null);
    }

    /**
     * Creates a new color option with separate fill and outline opacity and a parent option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value for fill
     * @param outlineOpacity The opacity value for outline
     * @param parent The parent option, or null if this is a root option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity, Option<?> parent) {
        this(owner, name, description, defaultColor, fillOpacity, outlineOpacity, false, () -> true, parent);
    }

    /**
     * Creates a new color option with single opacity and visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value used
     * @param visibility The visibility supplier for this option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, BooleanSupplier visibility) {
        this(owner, name, description, defaultColor, fillOpacity, fillOpacity, true, visibility, null);
    }

    /**
     * Creates a new color option with single opacity, visibility and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value used
     * @param visibility The visibility supplier for this option
     * @param parent The parent option, or null if this is a root option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, BooleanSupplier visibility, Option<?> parent) {
        this(owner, name, description, defaultColor, fillOpacity, fillOpacity, true, visibility, parent);
    }

    /**
     * Creates a new color option with separate fill and outline opacity and visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value for fill
     * @param outlineOpacity The opacity value for outline
     * @param visibility The visibility supplier for this option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity, BooleanSupplier visibility) {
        this(owner, name, description, defaultColor, fillOpacity, outlineOpacity, false, visibility, null);
    }

    /**
     * Creates a new color option with separate fill and outline opacity, visibility and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultColor The default color
     * @param fillOpacity The opacity value for fill
     * @param outlineOpacity The opacity value for outline
     * @param visibility The visibility supplier for this option
     * @param parent The parent option, or null if this is a root option
     */
    public ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity, BooleanSupplier visibility, Option<?> parent) {
        this(owner, name, description, defaultColor, fillOpacity, outlineOpacity, false, visibility, parent);
    }

    protected ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity, boolean singleOpacity) {
        this(owner, name, description, defaultColor, fillOpacity, outlineOpacity, singleOpacity, () -> true, null);
    }

    private ColorOption(BaseModule owner, String name, String description, ClientColor defaultColor, float fillOpacity, float outlineOpacity, boolean singleOpacity, BooleanSupplier visibility, Option<?> parent) {
        super(owner, name, description, visibility, parent);
        Objects.requireNonNull(defaultColor, "Default color cannot be null");
        this.singleOpacity = singleOpacity;
        this.defaultValue = new Value("_default", defaultColor.copy(), fillOpacity, outlineOpacity, singleOpacity);
        this.value = defaultValue.copy();
        if (this.value.color != null) {
            this.value.color.choose(this);
        }
    }

    @Override
    public Value getValue() {
        return value;
    }

    @Override
    public Value setValue(Value newValue) {
        if (newValue == null || newValue.color == null) {
            return value;
        }

        if (value != null && value.color != null) {
            value.color.unchoose(this);
        }

        String selectionName = newValue.name == null ? "_default" : newValue.name;
        if (!selectionName.startsWith("_default") && ColorMaker.get(selectionName) == null) {
            return value;
        }

        float fill = newValue.fillOpacity;
        float outline = singleOpacity ? fill : newValue.outlineOpacity;

        value = new Value(selectionName, newValue.color.copy(), fill, outline, singleOpacity);
        value.color.choose(this);
        return value;
    }

    @Override
    public Value reset() {
        if (value != null && value.color != null) {
            value.color.unchoose(this);
        }
        value = defaultValue.copy();
        if (value.color != null) {
            value.color.choose(this);
        }
        return value;
    }

    /**
     * Checks if this color option uses a single opacity value
     *
     * @return true if single opacity is used, false if separate fill and outline opacity values are used
     */
    public boolean isSingleOpacity() {
        return singleOpacity;
    }

    /**
     * Gets a copy of the default value for this option
     *
     * @return A copy of the default value
     */
    public Value getDefaultValue() {
        return defaultValue.copy();
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        String selectionName = value.name == null ? "_default" : value.name;
        if (selectionName.startsWith("_default")) {
            selectionName = "_default";
        }
        object.addProperty("selection", selectionName);
        object.addProperty("fillOpacity", value.fillOpacity);
        object.addProperty("outlineOpacity", value.outlineOpacity);
        return object;
    }

    @Override
    public Value fromJson(JsonObject object) {
        if (object == null) {
            return value;
        }

        String selection = object.has("selection") ? object.get("selection").getAsString() : "_default";
        float fill = object.has("fillOpacity") ? object.get("fillOpacity").getAsFloat() : value.fillOpacity;
        float outline = object.has("outlineOpacity") ? object.get("outlineOpacity").getAsFloat() : value.outlineOpacity;

        if (selection.startsWith("_default")) {
            Value current = reset();
            current.fillOpacity = fill;
            current.outlineOpacity = singleOpacity ? fill : outline;
            return current;
        }

        ClientColor color = ColorMaker.get(selection);
        if (color == null) {
            Value current = reset();
            current.fillOpacity = fill;
            current.outlineOpacity = singleOpacity ? fill : outline;
            return current;
        }

        return setValue(new Value(selection, color, fill, outline, singleOpacity));
    }

    @Override
    public void onClientColorDeleted() {
        reset();
    }

    /**
     * Color selection value stored by the option.
     */
    public static class Value {
        /**
         * The name/identifier of the selected color
         */
        public final String name;

        /**
         * The selected color
         */
        public final ClientColor color;

        /**
         * The opacity value for fill operations
         */
        public float fillOpacity;

        /**
         * The opacity value for outline operations
         */
        public float outlineOpacity;

        /**
         * Whether this value uses single opacity
         */
        public final boolean singleOpacity;

        /**
         * Creates a new value with single opacity
         *
         * @param name The name/identifier of the color
         * @param color The color
         * @param fillOpacity The opacity value used
         */
        public Value(String name, ClientColor color, float fillOpacity) {
            this(name, color, fillOpacity, fillOpacity, true);
        }

        /**
         * Creates a new value with separate fill and outline opacity
         *
         * @param name The name/identifier of the color
         * @param color The color
         * @param fillOpacity The opacity value for fill
         * @param outlineOpacity The opacity value for outline
         */
        public Value(String name, ClientColor color, float fillOpacity, float outlineOpacity) {
            this(name, color, fillOpacity, outlineOpacity, false);
        }

        private Value(String name, ClientColor color, float fillOpacity, float outlineOpacity, boolean singleOpacity) {
            this.name = name;
            this.color = color;
            this.fillOpacity = fillOpacity;
            this.outlineOpacity = outlineOpacity;
            this.singleOpacity = singleOpacity;
        }

        /**
         * Creates a copy of this value
         *
         * @return A new Value instance with the same properties
         */
        public Value copy() {
            return new Value(name, color != null ? color.copy() : null, fillOpacity, outlineOpacity, singleOpacity);
        }
    }
}
