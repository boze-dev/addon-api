package dev.boze.api.config;

import dev.boze.api.addon.Addon;

/**
 * Interface for saving and loading json objects
 */
public interface JsonTools {

    /**
     * Saves an object to a file
     *
     * @param addon The addon to save the object for
     * @param fileName The name of the file to save the object to
     * @param object The object to save
     * @return If the object was saved successfully
     */
    boolean saveObject(Addon addon, String fileName, Serializable<?> object);

    /**
     * Loads to object from a file
     *
     * @param addon The addon to load the object for
     * @param fileName The name of the file to load the object from
     * @param object The object to load to
     * @return The loaded object, or null if the object failed to load
     */
    <T extends Serializable<T>> T loadObject(Addon addon, String fileName, Serializable<T> object);
}
