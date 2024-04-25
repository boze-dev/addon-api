package dev.boze.api.setting;

import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.config.Serializable;

public abstract class SettingBase<T> implements Serializable<T> {

    public final ToggleableModule owner;

    public final String name;
    public final String description;

    public SettingBase(ToggleableModule owner, String name, String description) {
        this.owner = owner;
        owner.settings.add(this);

        this.name = name;
        this.description = description;
    }

    public abstract T getValue();

    public abstract T setValue(T newValue);

    public abstract T reset();
}
