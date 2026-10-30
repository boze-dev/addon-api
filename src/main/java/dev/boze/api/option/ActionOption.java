package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;

import java.util.function.BooleanSupplier;

/**
 * A button. It holds no value and saves nothing; the GUI runs its action when clicked.
 */
public class ActionOption extends Option<Boolean> {
    private final Runnable action;

    /**
     * @param owner       The module that owns this option
     * @param name        The name of this option
     * @param description The description of this option
     * @param action      What clicking the button does
     */
    public ActionOption(BaseModule owner, String name, String description, Runnable action) {
        super(owner, name, description);
        this.action = action;
    }

    /**
     * @param owner       The module that owns this option
     * @param name        The name of this option
     * @param description The description of this option
     * @param action      What clicking the button does
     * @param visibility  The visibility supplier for this option
     */
    public ActionOption(BaseModule owner, String name, String description, Runnable action, BooleanSupplier visibility) {
        super(owner, name, description, visibility);
        this.action = action;
    }

    /**
     * Runs the action.
     */
    public void run() {
        action.run();
    }

    @Override
    public Boolean getValue() {
        return false;
    }

    /**
     * Runs the action when {@code newValue} is true.
     *
     * @return Always false
     */
    @Override
    public Boolean setValue(Boolean newValue) {
        if (Boolean.TRUE.equals(newValue)) run();
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
