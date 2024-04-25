package dev.boze.api.internal.interfaces;

import dev.boze.api.exception.ModuleNotFoundException;

public interface IModules {
    boolean getState(String module) throws ModuleNotFoundException;

    void setState(String module, boolean state) throws ModuleNotFoundException;
}
