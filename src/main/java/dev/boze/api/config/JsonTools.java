package dev.boze.api.config;

import dev.boze.api.addon.Addon;
import dev.boze.api.internal.Instances;

/**
 * Interface for saving and loading json objects
 */
public final class JsonTools {

    /**
     * Saves an object to a file
     *
     * @param addon The addon to save the object for
     * @param fileName The name of the file to save the object to
     * @param object The object to save
     * @return If the object was saved successfully
     */
    public static boolean saveObject(Addon addon, String fileName, Serializable<?> object) {
        return Instances.getJson().saveObject(addon, fileName, object);
    }

    /**
     * Loads to object from a file
     *
     * @param addon The addon to load the object for
     * @param fileName The name of the file to load the object from
     * @param object The object to load to
     * @return The loaded object, or null if the object failed to load
     */
    public static <T extends Serializable<T>> T loadObject(Addon addon, String fileName, Serializable<T> object) {
        return Instances.getJson().loadObject(addon, fileName, object);
    }
}
