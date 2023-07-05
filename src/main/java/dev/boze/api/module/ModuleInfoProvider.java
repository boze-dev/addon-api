package dev.boze.api.module;

/**
 * Module Info Provider, used to get module info by name for Boze and addon modules
 */
public interface ModuleInfoProvider {

    /**
     *
     * @return module info by name, or null if not found
     */
    ModuleInfo getInfo(String moduleName);
}
