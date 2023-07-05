package dev.boze.api.module;

import dev.boze.api.input.Bind;

/**
 * ModuleInfo interface
 *
 * This interface is used by addon modules to provide information about themselves to the client
 *
 * Addon modules can also get information about other modules using this interface
 * @see dev.boze.api.module.ModuleInfoProvider
 */
public interface ModuleInfo {

    String getName();

    String getTitle();

    String getDescription();

    boolean getState();

    boolean setState(boolean newState);

    Bind getBind();

    void setBind(Bind newBind);
}
