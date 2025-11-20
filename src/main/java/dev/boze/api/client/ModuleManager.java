package dev.boze.api.client;

import dev.boze.api.BozeInstance;
import dev.boze.api.addon.AddonModule;
import dev.boze.api.client.module.ClientModule;
import dev.boze.api.internal.Instances;
import dev.boze.api.client.module.BaseModule;

import java.util.ArrayList;
import java.util.List;

/**
 * ModuleManager provides a way to interact with Boze modules
 */
public final class ModuleManager {

    private static List<BaseModule> cachedAllModules = null;
    private static int cachedAddonModulesSize = -1;

    /**
     * Get the state of a module
     *
     * @param name The name of the module to get the state of
     * @return The state of the module
     * @throws IllegalArgumentException If the module is not found
     */
    public static boolean getState(String name) throws IllegalArgumentException {
        return Instances.getModules().getState(name);
    }

    /**
     * Set the state of a module
     *
     * @param name The name of the module to set the state of
     * @param state The state to set the module to
     * @throws IllegalArgumentException If the module is not found
     */
    public static boolean setState(String name, boolean state) throws IllegalArgumentException {
        return Instances.getModules().setState(name, state);
    }

    /**
     * @param name The name of the module to get
     * @return The module
     */
    public static BaseModule getModule(String name) {
        ClientModule clientModule = getClientModule(name);
        if (clientModule != null) {
            return clientModule;
        }

        return getAddonModule(name);
    }

    /**
     * @return A list of all modules
     */
    public static List<BaseModule> getModules() {
        List<AddonModule> addonModules = getAddonModules();

        if (cachedAllModules == null || cachedAddonModulesSize != addonModules.size()) {
            cachedAllModules = new ArrayList<>();

            cachedAllModules.addAll(getClientModules());
            cachedAllModules.addAll(addonModules);

            cachedAddonModulesSize = addonModules.size();
        }

        return cachedAllModules;
    }

    /**
     * @param name The name of the addon module to get
     * @return The addon module
     */
    public static AddonModule getAddonModule(String name) {
        for (AddonModule module : BozeInstance.INSTANCE.getModules()) {
            if (module.getName().equals(name)) return module;
        }
        return null;
    }

    /**
     * @return A list of all addon modules
     */
    public static List<AddonModule> getAddonModules() {
        return BozeInstance.INSTANCE.getModules();
    }

    /**
     * @param name The name of the client module to get
     * @return The client module
     */
    public static ClientModule getClientModule(String name) {
        return Instances.getModules().getClientModule(name);
    }

    /**
     * @return A list of all client modules
     */
    public static List<ClientModule> getClientModules() {
        return Instances.getModules().getClientModules();
    }
}
