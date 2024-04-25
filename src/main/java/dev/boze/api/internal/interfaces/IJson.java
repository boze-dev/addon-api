package dev.boze.api.internal.interfaces;

import dev.boze.api.addon.Addon;
import dev.boze.api.config.Serializable;

public interface IJson {
    boolean saveObject(Addon addon, String fileName, Serializable<?> object);

    <T extends Serializable<T>> T loadObject(Addon addon, String fileName, Serializable<T> object);
}
