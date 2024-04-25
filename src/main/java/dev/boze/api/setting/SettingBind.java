package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.input.Bind;

public class SettingBind extends SettingBase<Bind> implements Bind {

    private int bind;
    private boolean isButton;

    private final int defaultBind;
    private final boolean defaultIsButton;

    public SettingBind(ToggleableModule owner, String name, String description) {
        this(owner, name, description, -1, false);
    }

    public SettingBind(ToggleableModule owner, String name, String description, int bind, boolean isButton) {
        super(owner, name, description);
        this.bind = bind;
        this.isButton = isButton;

        this.defaultBind = bind;
        this.defaultIsButton = isButton;
    }

    @Override
    public Bind getValue() {
        return this;
    }

    @Override
    public Bind setValue(Bind newValue) {
        bind = newValue.getBind();
        isButton = newValue.isButton();
        return this;
    }

    @Override
    public Bind reset() {
        bind = defaultBind;
        isButton = defaultIsButton;
        return this;
    }

    @Override
    public int getBind() {
        return bind;
    }

    @Override
    public boolean isButton() {
        return isButton;
    }

    public void setBind(Bind bind) {
        this.bind = bind.getBind();
        this.isButton = bind.isButton();
    }

    public void setBind(int bind, boolean isButton) {
        this.bind = bind;
        this.isButton = isButton;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("bind", bind);
        object.addProperty("isButton", isButton);
        return object;
    }

    @Override
    public SettingBind fromJson(JsonObject object) {
        bind = object.get("bind").getAsInt();
        isButton = object.get("isButton").getAsBoolean();
        return this;
    }
}
