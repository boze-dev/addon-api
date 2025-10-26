package dev.boze.api.utility;

import dev.boze.api.internal.Instances;

/**
 * ProfileHelper provides a way to interact with Boze profiles
 */
public final class ProfileHelper {

    /**
     * Enum representing the four categories of profiles
     */
    public enum ProfileCategory {
        MAIN,
        VISUALS,
        BINDS,
        CLIENT
    }

    /**
     * Get the selected profile name for a specified category
     *
     * @param category The profile category
     * @return The selected profile name
     */
    public static String getSelectedProfile(ProfileCategory category) {
        return Instances.getProfiles().getSelectedProfile(category);
    }

    /**
     * Get the available profiles for a specified category
     *
     * @param category The profile category
     * @return Array of available profile names
     */
    public static String[] getAvailableProfiles(ProfileCategory category) {
        return Instances.getProfiles().getAvailableProfiles(category);
    }
} 