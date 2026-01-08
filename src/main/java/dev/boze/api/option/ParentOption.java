package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;

import java.util.function.BooleanSupplier;

/**
 * An option that serves as a parent container for other options
 * <br>
 * ParentOption provides a way to group related options together
 * <br>
 * It doesn't store a meaningful value itself, but serves as a container for child options
 */
public class ParentOption extends Option<Boolean> {

    /**
     * Creates a new parent option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public ParentOption(BaseModule owner, String name, String description) {
        super(owner, name, description, () -> true, null);
    }

    /**
     * Creates a new parent option with visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param visibility The visibility supplier for this option
     */
    public ParentOption(BaseModule owner, String name, String description, BooleanSupplier visibility) {
        super(owner, name, description, visibility, null);
    }

    @Override
    public Boolean getValue() {
        return false;
    }

    @Override
    public Boolean setValue(Boolean newValue) {
        return false;
    }

    @Override
    public Boolean reset() {
        return false;
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject();
    }

    @Override
    public Boolean fromJson(JsonObject object) {
        return false;
    }
}
