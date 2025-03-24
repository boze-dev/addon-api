package dev.boze.api.addon;

import dev.boze.api.Globals;
import dev.boze.api.config.JsonTools;
import dev.boze.api.config.Serializable;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Base class for all Boze addons
 * <p></p>
 * An addon is a self-contained extension that can add new functionality to Boze
 * <p></p>
 * Each addon has a unique identifier, name, description, and version
 * <p></p>
 * Addons can contain multiple modules and commands, which are managed through the addon's dispatcher
 */
public abstract class Addon implements Serializable<Addon> {
    /**
     * Unique identifier for this addon
     */
    public final String id;

    /**
     * Display name of this addon
     */
    public final String name;

    /**
     * Description of what this addon does
     */
    public final String description;

    /**
     * Version string of this addon
     */
    public final String version;

    /**
     * List of modules provided by this addon
     */
    public final ArrayList<AddonModule> modules = new ArrayList<>();

    /**
     * Command dispatcher for this addon
     */
    public final AddonDispatcher dispatcher;

    /**
     * Creates a new addon with a default dispatcher
     *
     * @param id Unique identifier for this addon
     * @param name Display name of this addon
     * @param description Description of what this addon does
     * @param version Version string of this addon
     */
    public Addon(String id, String name, String description, String version) {
        this(id, name, description, version, new AddonDispatcher());
    }

    /**
     * Creates a new addon with a custom dispatcher
     *
     * @param id Unique identifier for this addon
     * @param name Display name of this addon
     * @param description Description of what this addon does
     * @param version Version string of this addon
     * @param dispatcher Custom command dispatcher for this addon
     */
    public Addon(String id, String name, String description, String version, AddonDispatcher dispatcher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
        this.dispatcher = dispatcher;
    }

    /**
     * Initializes this addon
     * <p></p>
     * This is called when the addon is loaded. The default implementation loads the addon's
     * configuration from disk.
     *
     * @return true if initialization was successful, false otherwise
     */
    public boolean initialize() {
        JsonTools.loadObject(this, "config", this);
        return true;
    }

    /**
     * Shuts down this addon
     * <p></p>
     * This is called when the addon is unloaded. The default implementation saves the addon's
     * configuration to disk.
     */
    public void shutdown() {
        JsonTools.saveObject(this, "config", this);
    }

    /**
     * Gets the addon's directory
     * <p></p>
     * Creates the directory if it doesn't exist
     *
     * @return The addon's directory
     */
    public File getDir() {
        File dir = new File(Globals.getAddonDir(), id);
        if (!dir.exists()) dir.mkdir();
        return dir;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        
        // Add basic properties
        object.addProperty("id", id);
        object.addProperty("name", name);
        object.addProperty("description", description);
        object.addProperty("version", version);
        
        // Add modules as an object of JsonObjects with module names as keys
        if (!modules.isEmpty()) {
            JsonObject modulesObject = new JsonObject();
            for (AddonModule module : modules) {
                modulesObject.add(module.getName(), module.toJson());
            }
            object.add("modules", modulesObject);
        }
        
        return object;
    }

    @Override
    public Addon fromJson(JsonObject object) {
        // Modules (id, name, description, version are final and set in constructor)
        if (object.has("modules") && object.get("modules").isJsonObject()) {
            JsonObject modulesObject = object.getAsJsonObject("modules");
            
            for (AddonModule module : modules) {
                String moduleName = module.getName();
                if (modulesObject.has(moduleName)) {
                    module.fromJson(modulesObject.getAsJsonObject(moduleName));
                }
            }
        }
        
        return this;
    }
}
