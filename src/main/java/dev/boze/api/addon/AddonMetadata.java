package dev.boze.api.addon;

import java.util.Objects;

/**
 * The addon's metadata
 *
 * @param id The addon's id
 *             This should be unique, and should not contain spaces
 * @param name The addon's name
 * @param description The addon's description
 *             This should be a short description of the addon
 * @param version The addon's version
 */
public record AddonMetadata(String id, String name, String description, AddonVersion version) {

    /**
     * Checks if two addons have the same id and version
     * This is used to check if two addons are the same
     *
     * @param o The other addon
     * @return Weather the two addons are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddonMetadata that = (AddonMetadata) o;
        return Objects.equals(id, that.id) && Objects.equals(version, that.version);
    }
}
