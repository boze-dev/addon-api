package dev.boze.api.option;

import dev.boze.api.addon.AddonModule;
import dev.boze.api.config.Serializable;

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
    public final AddonModule owner;

    /**
     * The name of this option
     */
    public final String name;

    /**
     * The description of this option
     */
    public final String description;

    /**
     * Creates a new option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public Option(AddonModule owner, String name, String description) {
        this.owner = owner;
        owner.settings.add(this);

        this.name = name;
        this.description = description;
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
}
