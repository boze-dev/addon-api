package dev.boze.api.addon.module;

import dev.boze.api.addon.gui.AddonElement;
import dev.boze.api.module.ModuleInfo;

import java.util.List;

/**
 * AddonModule interface
 *
 * This interface should be implemented by all addon modules
 *
 * It's recommended you make a base module class, instead of implementing this interface directly for each module
 *
 */
public interface AddonModule {

    /**
     *
     * @return the module info of the module
     */
    ModuleInfo getInfo();

    /**
     *
     * @return a list of addon gui elements that should be displayed
     */
    List<AddonElement> getElements();
}
