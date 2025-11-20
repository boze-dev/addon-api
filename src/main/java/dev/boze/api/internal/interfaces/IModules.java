package dev.boze.api.internal.interfaces;

import dev.boze.api.client.module.ClientModule;

import java.util.List;

public interface IModules {
    boolean getState(String name) throws IllegalArgumentException;

    boolean setState(String name, boolean state) throws IllegalArgumentException;

    ClientModule getClientModule(String name);

    List<ClientModule> getClientModules();
}
