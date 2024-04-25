package dev.boze.api.addon;

import dev.boze.api.Globals;
import dev.boze.api.addon.command.AddonDispatcher;
import dev.boze.api.addon.command.DefaultDispatcher;
import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.api.config.JsonTools;
import dev.boze.api.config.Serializable;

import java.io.File;
import java.util.ArrayList;

public abstract class Addon implements Serializable<Addon> {
    public final String id;
    public final String name;

    public final String description;

    public final String version;

    public final ArrayList<ToggleableModule> modules = new ArrayList<>();

    public final AddonDispatcher dispatcher;

    public Addon(String id, String name, String description, String version) {
        this(id, name, description, version, new DefaultDispatcher(id));
    }

    public Addon(String id, String name, String description, String version, AddonDispatcher dispatcher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
        this.dispatcher = dispatcher;
    }

    public boolean initialize() {
        JsonTools.loadObject(this, "config", this);
        return true;
    }

    public void shutdown() {
        JsonTools.saveObject(this, "config", this);
    }

    public File getDir() {
        File dir = new File(Globals.getAddonDir(), id);
        if (!dir.exists()) dir.mkdir();
        return dir;
    }
}
