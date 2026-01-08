package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;

import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * An option for selecting from a predefined set of modes
 * <br>
 * ModeOption provides a way to store and modify enum values
 * <br>
 * The available modes are defined by the enum type parameter
 *
 * @param <T> The enum type defining the available modes
 */
public class ModeOption<T extends Enum<T>> extends Option<T> {

    private T value;
    private final T defaultValue;

    /**
     * Creates a new mode option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultValue The initial value and default value
     */
    public ModeOption(BaseModule owner, String name, String description, T defaultValue) {
        this(owner, name, description, defaultValue, () -> true, null);
    }

    /**
     * Creates a new mode option with a parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultValue The initial value and default value
     * @param parent The parent option, or null if this is a root option
     */
    public ModeOption(BaseModule owner, String name, String description, T defaultValue, Option<?> parent) {
        super(owner, name, description, parent);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    /**
     * Creates a new mode option with visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultValue The initial value and default value
     * @param visibility The visibility supplier for this option
     */
    public ModeOption(BaseModule owner, String name, String description, T defaultValue, BooleanSupplier visibility) {
        this(owner, name, description, defaultValue, visibility, null);
    }

    /**
     * Creates a new mode option with visibility and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param defaultValue The initial value and default value
     * @param visibility The visibility supplier for this option
     * @param parent The parent option, or null if this is a root option
     */
    public ModeOption(BaseModule owner, String name, String description, T defaultValue, BooleanSupplier visibility, Option<?> parent) {
        super(owner, name, description, visibility, parent);
        this.value = defaultValue;
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the enum class of this mode option
     *
     * @return The enum class
     */
    @SuppressWarnings("unchecked")
    public Class<T> getEnumClass() {
        return (Class<T>) value.getClass();
    }

    /**
     * Gets a list of all available mode names
     *
     * @return List of mode names
     */
    public List<String> getModes() {
        return Arrays.stream(getEnumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    /**
     * Gets the name of the current mode
     *
     * @return The current mode name
     */
    public String getModeName() {
        return value.name();
    }

    /**
     * Gets the name of the current value
     *
     * @return The current value name
     */
    public String getValueName() {
        return value.name();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T setValue(T newValue) {
        this.value = newValue;
        return value;
    }

    @Override
    public T reset() {
        value = defaultValue;
        return value;
    }

    /**
     * Sets the current mode by name
     * <p>
     * The name is case-insensitive
     *
     * @param name The name of the mode to set
     */
    public void setValueByName(String name) {
        for (T constant : getEnumClass().getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(name)) {
                value = constant;
                break;
            }
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("mode", value.name());
        return object;
    }

    @Override
    public T fromJson(JsonObject object) {
        if (object.has("mode")) {
            String modeName = object.get("mode").getAsString();
            setValueByName(modeName);
        }
        return value;
    }
}
