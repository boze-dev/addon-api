package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.utility.input.Bind;
import dev.boze.api.client.module.BaseModule;

import java.util.function.BooleanSupplier;

/**
 * An option for keyboard/mouse bindings
 * <br>
 * BindOption provides a way to store and modify key or mouse button bindings
 * <br>
 * Can be bound to either a keyboard key or mouse button
 */
public class BindOption extends Option<Bind> {

    private Bind bindInstance;
    private final Bind defaultBindInstance;

    /**
     * Creates a new bind option with no default binding
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public BindOption(BaseModule owner, String name, String description) {
        this(owner, name, description, -1, false);
    }

    /**
     * Creates a new bind option with a specified default binding
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param bind The key/button code to bind to
     * @param isButton Whether this is a mouse button (true) or keyboard key (false)
     */
    public BindOption(BaseModule owner, String name, String description, int bind, boolean isButton) {
        super(owner, name, description);
        this.bindInstance = new Bind(isButton, bind);
        this.defaultBindInstance = new Bind(isButton, bind);
    }

    /**
     * Creates a new bind option with a specified default binding and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param bind The key/button code to bind to
     * @param isButton Whether this is a mouse button (true) or keyboard key (false)
     * @param parent The parent option, or null if this is a root option
     */
    public BindOption(BaseModule owner, String name, String description, int bind, boolean isButton, Option<?> parent) {
        super(owner, name, description, parent);
        this.bindInstance = new Bind(isButton, bind);
        this.defaultBindInstance = new Bind(isButton, bind);
    }

    /**
     * Creates a new bind option with visibility
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param bind The key/button code to bind to
     * @param isButton Whether this is a mouse button (true) or keyboard key (false)
     * @param visibility The visibility supplier for this option
     */
    public BindOption(BaseModule owner, String name, String description, int bind, boolean isButton, BooleanSupplier visibility) {
        this(owner, name, description, bind, isButton, visibility, null);
    }

    /**
     * Creates a new bind option with visibility and parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param bind The key/button code to bind to
     * @param isButton Whether this is a mouse button (true) or keyboard key (false)
     * @param visibility The visibility supplier for this option
     * @param parent The parent option, or null if this is a root option
     */
    public BindOption(BaseModule owner, String name, String description, int bind, boolean isButton, BooleanSupplier visibility, Option<?> parent) {
        super(owner, name, description, visibility, parent);
        this.bindInstance = new Bind(isButton, bind);
        this.defaultBindInstance = new Bind(isButton, bind);
    }

    @Override
    public Bind getValue() {
        return bindInstance;
    }

    @Override
    public Bind setValue(Bind newValue) {
        bindInstance = new Bind(newValue.isButton(), newValue.getBind());
        return bindInstance;
    }

    @Override
    public Bind reset() {
        bindInstance = new Bind(defaultBindInstance.isButton(), defaultBindInstance.getBind());
        return bindInstance;
    }

    /**
     * Gets the current key/button code
     *
     * @return The key/button code
     */
    public int getBind() {
        return bindInstance.getBind();
    }

    /**
     * Checks if this bind is for a mouse button
     *
     * @return true if this is a mouse button bind, false if it's a keyboard key
     */
    public boolean isButton() {
        return bindInstance.isButton();
    }

    /**
     * Sets the binding using a Bind object
     *
     * @param bind The new binding
     */
    public void setBind(Bind bind) {
        bindInstance = new Bind(bind.isButton(), bind.getBind());
    }

    /**
     * Sets the binding using a key/button code and type
     *
     * @param bind The key/button code
     * @param isButton Whether this is a mouse button (true) or keyboard key (false)
     */
    public void setBind(int bind, boolean isButton) {
        bindInstance = new Bind(isButton, bind);
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("bind", bindInstance.getBind());
        object.addProperty("isButton", bindInstance.isButton());
        return object;
    }

    @Override
    public Bind fromJson(JsonObject object) {
        int bind = object.get("bind").getAsInt();
        boolean isButton = object.get("isButton").getAsBoolean();
        bindInstance = new Bind(isButton, bind);
        return bindInstance;
    }
}
