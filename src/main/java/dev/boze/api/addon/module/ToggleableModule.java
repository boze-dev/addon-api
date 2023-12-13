package dev.boze.api.addon.module;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.addon.gui.AddonElement;
import dev.boze.api.config.Serializable;
import dev.boze.api.input.Bind;
import dev.boze.api.module.ModuleInfo;
import dev.boze.api.setting.SettingBind;

import java.util.ArrayList;
import java.util.List;

public class ToggleableModule implements AddonModule, ModuleInfo, Serializable<ToggleableModule> {

    private final String name;
    private final String description;

    private String title;

    private boolean state;

    private SettingBind bind;

    protected final ArrayList<AddonElement> elements = new ArrayList<>();

    protected ToggleableModule(String name, String description) {
        this.name = name;
        this.description = description;

        this.title = name;

        this.state = false;

        this.bind = new SettingBind();
    }

    @Override
    public ModuleInfo getInfo() {
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean getState() {
        return state;
    }

    @Override
    public boolean setState(boolean newState) {
        if (newState == state) return false;

        state = newState;

        if (state) {
            BozeInstance.INSTANCE.subscribe(this);
        } else {
            BozeInstance.INSTANCE.unsubscribe(this);
        }

        return true;
    }

    protected void onEnable() {

    }

    protected void onDisable() {

    }

    @Override
    public Bind getBind() {
        return bind;
    }

    @Override
    public void setBind(Bind newBind) {
        this.bind.setBind(newBind);
    }

    @Override
    public List<AddonElement> getElements() {
        return elements;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("title", title);
        object.addProperty("state", state);
        object.add("bind", bind.toJson());

        for (AddonElement element : elements) {
            if (element instanceof Serializable) {
                object.add(element.getName(), ((Serializable<?>) element).toJson());
            }
        }

        return object;
    }

    @Override
    public ToggleableModule fromJson(JsonObject object) {
        title = object.get("title").getAsString();
        state = object.get("state").getAsBoolean();
        bind = new SettingBind().fromJson(object.get("bind").getAsJsonObject());

        for (AddonElement element : elements) {
            if (element instanceof Serializable) {
                ((Serializable<?>) element).fromJson(object.get(element.getName()).getAsJsonObject());
            }
        }

        return this;
    }
}
