package dev.boze.api.setting;

import com.google.gson.JsonObject;
import dev.boze.api.config.Serializable;
import dev.boze.api.input.Bind;

public class SettingBind implements Bind, Serializable<SettingBind> {

    private int bind;
    private boolean isButton;

    public SettingBind() {
        this(-1, false);
    }

    public SettingBind(int bind, boolean isButton) {
        this.bind = bind;
        this.isButton = isButton;
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
