package dev.boze.api.internal.interfaces;

import dev.boze.api.client.cape.CapeSource;

public interface ICapes {
    void addSource(CapeSource source);

    void removeSource(CapeSource source);

    String[] getSources();
}
