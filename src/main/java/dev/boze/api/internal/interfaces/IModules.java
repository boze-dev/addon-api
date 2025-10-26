package dev.boze.api.internal.interfaces;

import dev.boze.api.client.module.ClientModule;
import dev.boze.api.exception.ClientModuleNotFoundException;

import java.util.List;

public interface IModules {
    boolean getState(String name) throws ClientModuleNotFoundException;

    void setState(String name, boolean state) throws ClientModuleNotFoundException;

    ClientModule getClientModule(String name);

    List<ClientModule> getClientModules();
}
