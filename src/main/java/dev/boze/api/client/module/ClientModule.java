package dev.boze.api.client.module;

import dev.boze.api.option.Option;

import java.util.List;

/**
 * Base class for all client modules
 */
public abstract class ClientModule implements BaseModule {

    /**
     * Gets options provided by the specified extension for this module
     *
     * @param extension The extension to get options for
     * @return List of options provided by the extension
     */
    public abstract List<Option<?>> getExtensionOptions(ClientModuleExtension extension);
}
