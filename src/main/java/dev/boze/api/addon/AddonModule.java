package dev.boze.api.addon;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.config.Serializable;
import dev.boze.api.input.Bind;
import dev.boze.api.option.Option;
import dev.boze.api.option.BindOption;

import java.util.ArrayList;

/**
 * Base class for all addon modules
 * <p></p>
 * A module is a self-contained piece of functionality that can be enabled or disabled
 * <p></p>
 * Each module has a name, description, title (which can be changed), and a keybind
 * <p></p>
 * Modules can have settings which are automatically serialized and deserialized
 */
public abstract class AddonModule implements Serializable<AddonModule> {

    /**
     * Internal name of this module (cannot be changed)
     */
    private final String name;

    /**
     * Description of what this module does
     */
    private final String description;

    /**
     * Display title of this module (can be changed)
     */
    private String title;

    /**
     * Whether this module is currently enabled
     */
    private boolean state;

    /**
     * Keybind for toggling this module
     */
    private final BindOption bind;

    /**
     * List of settings for this module
     */
    public final ArrayList<Option<?>> settings = new ArrayList<>();

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
    public String getName() {
        return name;
    }

    /**
     * Gets the display title of this module
     *
     * @return The module's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the display title of this module
     *
     * @param newTitle The new title
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * Gets the description of this module
     *
     * @return The module's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets whether this module is enabled
     *
     * @return true if the module is enabled, false otherwise
     */
    public boolean getState() {
        return state;
    }

    /**
     * Sets whether this module is enabled
     * <p></p>
     * This will call onEnable() or onDisable() as appropriate
     *
     * @param newState The new state
     * @return true if the state changed, false if it was already in that state
     */
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

        return true;
    }

    /**
     * Called when this module is enabled
     * <p></p>
     * Override this to add custom enable behavior
     */
    protected void onEnable() {
    }

    /**
     * Called when this module is disabled
     * <p></p>
     * Override this to add custom disable behavior
     */
    protected void onDisable() {
    }

    /**
     * Gets the current keybind
     *
     * @return The module's keybind
     */
    public Bind getBind() {
        return bind.getValue();
    }

    /**
     * Gets the keybind option
     *
     * @return The module's keybind option
     */
    public BindOption getBindOption() {
        return bind;
    }

    /**
     * Sets the keybind
     *
     * @param newBind The new keybind
     */
    public void setBind(Bind newBind) {
        this.bind.setBind(newBind);
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("state", state);

        for (Option<?> setting : settings) {
            object.add(setting.name, setting.toJson());
        }

        return object;
    }

    @Override
    public AddonModule fromJson(JsonObject object) {
        title = object.get("title").getAsString();
        setState(object.get("state").getAsBoolean());

        for (Option<?> setting : settings) {
            setting.fromJson(object.get(setting.name).getAsJsonObject());
        }

        return this;
    }
}
