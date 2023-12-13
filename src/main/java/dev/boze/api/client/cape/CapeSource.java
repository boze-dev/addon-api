package dev.boze.api.client.cape;

import com.mojang.authlib.GameProfile;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.util.Objects;

public abstract class CapeSource {

    public final String name;

    public CapeSource(String name) {
        this.name = name;
    }

    /**
     * Returns url (to a png) for a given profile
     *
     * @param profile Profile to get cape url for
     * @return the url of the cape, or null if player has no cape
     */
    public abstract URL getUrl(GameProfile profile);

    /**
     * Gets called once a profile's cape gets loaded (if URL not null)
     *
     * @param profile Profile which got loaded
     * @param result Cape load result
     * @param identifier Identifier of the cape (null if not loaded successfully)
     */
    public abstract void callback(GameProfile profile, CapeLoadResult result, @Nullable Identifier identifier);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapeSource that = (CapeSource) o;
        return Objects.equals(name, that.name);
    }
}
