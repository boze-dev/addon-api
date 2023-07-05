package dev.boze.api.addon;

/**
 * Addon version
 *
 * Represents an addon's version
 *
 * @param major The major version
 *              This should be incremented when major change are made, that are not backwards compatible
 * @param minor The minor version
 *              This should be incremented when minor changes are made, that are backwards compatible
 * @param patch The patch version
 *              This should be incremented when a small patch is made
 */
public record AddonVersion(int major, int minor, int patch) {

    @Override
    public String toString() {
        return major +
                "." + minor +
                "." + patch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddonVersion that = (AddonVersion) o;
        return major == that.major && minor == that.minor && patch == that.patch;
    }
}
