package dev.boze.api.addon.module;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.addon.event.EventToggleAddonModule;
import dev.boze.api.config.Serializable;
import dev.boze.api.input.Bind;
import dev.boze.api.setting.SettingBase;
import dev.boze.api.setting.SettingBind;

import java.util.ArrayList;

public class ToggleableModule implements Serializable<ToggleableModule> {

    private final String name;
    private final String description;

    private String title;

    private boolean state;

    private final SettingBind bind;

    public final ArrayList<SettingBase<?>> settings = new ArrayList<>();

    protected ToggleableModule(String name, String description) {
        this.name = name;
        this.description = description;

        this.title = name;

        this.state = false;

        this.bind = new SettingBind(this, "Bind", "Keybind for " + name);
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getDescription() {
        return description;
    }

    public boolean getState() {
        return state;
    }

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

        EventToggleAddonModule event = EventToggleAddonModule.get(this, state);
        BozeInstance.INSTANCE.post(event);

        return true;
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }

    public Bind getBind() {
        return bind;
    }

    public void setBind(Bind newBind) {
        this.bind.setBind(newBind);
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("state", state);
        object.add("bind", bind.toJson());

        for (SettingBase<?> setting : settings) {
            object.add(setting.name, setting.toJson());
        }

        return object;
    }

    @Override
    public ToggleableModule fromJson(JsonObject object) {
        title = object.get("title").getAsString();
        setState(object.get("state").getAsBoolean());
        bind.fromJson(object.get("bind").getAsJsonObject());

        for (SettingBase<?> setting : settings) {
            setting.fromJson(object.get(setting.name).getAsJsonObject());
        }

        return this;
    }
}
