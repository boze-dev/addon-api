package dev.boze.api.addon;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.utility.config.Serializable;
import dev.boze.api.event.EventModuleToggle;
import dev.boze.api.utility.input.Bind;
import dev.boze.api.client.module.BaseModule;
import dev.boze.api.option.Option;
import dev.boze.api.option.BindOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all addon modules
 * <br>
 * A module is a self-contained piece of functionality that can be enabled or disabled
 * <br>
 * Each module has a name, description, title (which can be changed), and a keybind
 * <br>
 * Modules can have settings which are automatically serialized and deserialized
 */
public abstract class AddonModule implements Serializable<AddonModule>, BaseModule {

    /**
     * Internal name of this module (cannot be changed)
     */
    private final String name;

    /**
     * Description of what this module does
     */
    private String description;

    /**
     * Display title of this module (can be changed)
     */
    private String title;

    /**
     * Whether this module is currently enabled
     */
    private boolean state;

    /**
     * Bind for toggling this module
     */
    private final BindOption bind;

    /**
     * List of settings for this module
     */
    public final ArrayList<Option<?>> options = new ArrayList<>();

    /**
     * Whether this module is visible in the GUI
     */
    private boolean visible = true;

    /**
     * Whether to show notifications when this module is toggled
     */
    private boolean notify = false;

    /**
     * Whether this module should only be active while the bind key is held
     */
    private boolean onlyWhileHolding = false;

    /**
     * Creates a new module
     *
     * @param name Internal name of this module
     * @param description Description of what this module does
     */
    protected AddonModule(String name, String description) {
        this.name = name;
        this.description = description;

        this.title = name;
        this.state = false;
        this.bind = new BindOption(this, "Bind", "Keybind for " + name);
    }

    /**
     * Gets the internal name of this module
     *
     * @return The module's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the display title of this module
     *
     * @return The module's title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the display title of this module
     *
     * @param newTitle The new title
     */
    @Override
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Gets the description of this module
     *
     * @return The module's description
     */
    @Override
    public String getDescription() {
        return description;
    }


    /**
     * Modify the description of the module
     * <br>
     * This resets when you re-launch
     * <br>
     * Supports newline escape sequence
     *
     * @param description Description to set
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets whether this module is enabled
     *
     * @return true if the module is enabled, false otherwise
     */
    @Override
    public boolean getState() {
        return state;
    }

    /**
     * Sets whether this module is enabled
     * <br>
     * This will call onEnable() or onDisable() as appropriate
     *
     * @param newState The new state
     * @return true if the state changed, false if it was already in that state
     */
    @Override
    public boolean setState(boolean newState) {
        if (newState == state) return false;

        state = newState;

        if (state) {
            onEnable();
            BozeInstance.INSTANCE.subscribe(this);
        } else {
            BozeInstance.INSTANCE.unsubscribe(this);
            onDisable();
        }

        BozeInstance.INSTANCE.post(EventModuleToggle.get(this));
        return true;
    }

    /**
     * Called when this module is enabled
     * <br>
     * Override this to add custom enable behavior
     */
    @Override
    public void onEnable() {
    }

    /**
     * Called when this module is disabled
     * <br>
     * Override this to add custom disable behavior
     */
    @Override
    public void onDisable() {
    }

    /**
     * Gets the current keybind
     *
     * @return The module's keybind
     */
    @Override
    public Bind getBind() {
        return bind.getValue();
    }

    /**
     * Sets the keybind
     *
     * @param newBind The new keybind
     */
    @Override
    public void setBind(Bind newBind) {
        this.bind.setBind(newBind);
    }

    /**
     * Method called by ArrayList to get info
     *
     * @return Info to show in ArrayList brackets
     */
    @Override
    public String getArrayListInfo() {
        return "";
    }

    /**
     * Gets whether this module is visible in the ArrayList
     *
     * @return true if the module is visible in ArrayList, false otherwise
     */
    @Override
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets whether this module is visible in the ArrayList
     *
     * @param visible true to make the module visible in ArrayList, false to hide it
     */
    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets whether notifications should be shown when this module is toggled
     *
     * @return true if notifications should be shown, false otherwise
     */
    @Override
    public boolean shouldNotify() {
        return notify;
    }

    /**
     * Sets whether notifications should be shown when this module is toggled
     *
     * @param notify true to show notifications, false to hide them
     */
    @Override
    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    /**
     * Gets whether this module should only be active while the bind key is held
     *
     * @return true if the module only activates while holding the key, false for toggle behavior
     */
    @Override
    public boolean isOnlyWhileHolding() {
        return onlyWhileHolding;
    }

    /**
     * Sets whether this module should only be active while the bind key is held
     *
     * @param onlyWhileHolding true for hold-to-activate, false for toggle behavior
     */
    @Override
    public void setOnlyWhileHolding(boolean onlyWhileHolding) {
        this.onlyWhileHolding = onlyWhileHolding;
    }

    /**
     * Gets a list of this module's options
     *
     * @return the list of this module's options
     */
    @Override
    public List<Option<?>> getOptions() {
        return options;
    }

    /**
     * Gets the keybind option
     *
     * @return The module's bind option
     */
    public BindOption getBindOption() {
        return bind;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("state", state);
        object.addProperty("visible", visible);
        object.addProperty("notify", notify);
        object.addProperty("onlyWhileHolding", onlyWhileHolding);

        for (Option<?> setting : options) {
            object.add(setting.name, setting.toJson());
        }

        return object;
    }

    @Override
    public AddonModule fromJson(JsonObject object) {
        title = object.get("title").getAsString();
        setState(object.get("state").getAsBoolean());
        visible = object.get("visible").getAsBoolean();
        notify = object.get("notify").getAsBoolean();
        onlyWhileHolding = object.get("onlyWhileHolding").getAsBoolean();

        for (Option<?> setting : options) {
            setting.fromJson(object.get(setting.name).getAsJsonObject());
        }

        return this;
    }
}
