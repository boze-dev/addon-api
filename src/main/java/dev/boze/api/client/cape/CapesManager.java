package dev.boze.api.client.cape;

/**
 * Capes manager interface
 */
public interface CapesManager {
    void addSource(CapeSource source);

    void removeSource(CapeSource source);

    String[] getSources();
}
