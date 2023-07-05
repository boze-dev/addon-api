package dev.boze.api.config;

import com.google.gson.JsonObject;

/**
 * Interface for serializable objects (settings, modules, etc.)
 *
 * @param <T> The type of the object
 */
public interface Serializable<T> {

    /**
     * @return The object as a JsonObject
     */
    JsonObject toJson();

    /**
     * @param object The JsonObject to deserialize
     * @return The deserialized object
     */
    T fromJson(JsonObject object);
}
