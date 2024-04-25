package dev.boze.api.client.cape;

import dev.boze.api.internal.Instances;

/**
 * Capes manager
 * <p>
 * Used to add and remove external cape sources
 */
public final class CapesManager {

    /**
     * Add a cape source
     *
     * @param source Cape source
     */
    public static void addSource(CapeSource source) {
        Instances.getCapes().addSource(source);
    }

    /**
     * Remove a cape source
     *
     * @param source Cape source
     */
    public static void removeSource(CapeSource source) {
        Instances.getCapes().removeSource(source);
    }

    /**
     * Get all cape sources, from all addons
     *
     * @return Cape sources
     */
    public static String[] getSources() {
        return Instances.getCapes().getSources();
    }
}
