package dev.boze.api.module;

import dev.boze.api.exception.ModuleNotFoundException;
import dev.boze.api.internal.Instances;

/**
 * ModuleHelper provides a way to interact with Boze modules
 */
public class ModuleHelper {

    /**
     * Get the state of a module
     *
     * @param module The name of the module to get the state of
     * @return The state of the module
     * @throws ModuleNotFoundException If the module is not found
     */
    public static boolean getState(String module) throws ModuleNotFoundException {
        return Instances.getModules().getState(module);
    }

    /**
     * Set the state of a module
     *
     * @param module The name of the module to set the state of
     * @param state The state to set the module to
     * @throws ModuleNotFoundException If the module is not found
     */
    public static void setState(String module, boolean state) throws ModuleNotFoundException {
        Instances.getModules().setState(module, state);
    }
}
