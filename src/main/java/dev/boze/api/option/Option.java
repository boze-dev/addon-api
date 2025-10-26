package dev.boze.api.option;

import dev.boze.api.addon.AddonModule;
import dev.boze.api.utility.config.Serializable;
import dev.boze.api.client.module.BaseModule;

import java.util.ArrayList;

/**
 * Base class for all module options
 * <p></p>
 * Options are used to store configurable values for modules
 * <p></p>
 * All options must implement getValue(), setValue(), and reset() methods
 * <p></p>
 * Options also implement Serializable to allow for saving/loading from config files
 *
 * @param <T> The type of value stored by this option
 */
public abstract class Option<T> implements Serializable<T> {

    /**
     * The module that owns this option
     */
    public final BaseModule owner;

    /**
     * The name of this option
     */
    public final String name;

    /**
     * The description of this option
     */
    public final String description;

    /**
     * The parent option, if this option is a child of another option
     */
    private Option<?> parent;

    /**
     * The child options of this option
     */
    private final ArrayList<Option<?>> children = new ArrayList<>();


    /**
     * Creates a new option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public Option(BaseModule owner, String name, String description) {
        this(owner, name, description, null);
    }

    /**
     * Creates a new option with a parent
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     * @param parent The parent option, or null if this is a root option
     */
    public Option(BaseModule owner, String name, String description, Option<?> parent) {
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.parent = parent;

        if (parent != null) {
            parent.children.add(this);
        }

        if (owner instanceof AddonModule addonModule) {
            addonModule.options.add(this);
        }
    }

    /**
     * Gets the full name of this option
     *
     * @return Name if no parent, ParentName.Name otherwise
     */
    public String getFullName() {
        if (parent != null) {
            return parent.getFullName() + "." + name;
        }
        return name;
    }

    /**
     * Gets the current value of this option
     *
     * @return The current value
     */
    public abstract T getValue();

    /**
     * Sets the value of this option
     *
     * @param newValue The new value to set
     * @return The value that was set (may be different from newValue if validation was performed)
     */
    public abstract T setValue(T newValue);

    /**
     * Resets this option to its default value
     *
     * @return The default value
     */
    public abstract T reset();


    /**
     * Gets the parent option of this option
     *
     * @return The parent option, or null if this is a root option
     */
    public Option<?> getParent() {
        return parent;
    }

    /**
     * Sets the parent option of this option
     *
     * @param parent the parent option, or null to make this a root option
     */
    public void setParent(Option<?> parent) {
        this.parent = parent;
    }

    /**
     * Checks if this option is a parent (has children)
     *
     * @return true if this option has children, false otherwise
     */
    public boolean isParent() {
        return !children.isEmpty();
    }
}
