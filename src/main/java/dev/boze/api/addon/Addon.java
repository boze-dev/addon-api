package dev.boze.api.addon;

import dev.boze.api.Globals;
import dev.boze.api.addon.command.AddonDispatcher;
import dev.boze.api.addon.module.AddonModule;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Interface for addons
 *
 * All addons must implement this
 */
public interface Addon {

    /**
     * @return The addon's metadata
     * @see dev.boze.api.addon.AddonMetadata
     */
    AddonMetadata getMetadata();

    /**
     * Initialize the addon
     *
     * @return If the addon initialized successfully
     */
    boolean initialize();

    /**
     * Shutdown the addon, save configs, etc.
     *
     * Called when client gets closed
     */
    void shutdown();

    /**
     * @return List of addon's modules
     */
    List<AddonModule> getModules();

    /**
     * @return The addon's dispatcher
     */
    AddonDispatcher getDispatcher();

    /**
     * @return The addon's directory
     */
    default File getDir() {
        File dir = new File(Globals.getAddonDir(), getMetadata().id());
        if (!dir.exists()) dir.mkdir();
        return dir;
    }
}
