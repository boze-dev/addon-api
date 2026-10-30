package dev.boze.api.gui.theme;

import com.google.gson.JsonObject;

/**
 * State a theme keeps between sessions, such as where panels were moved. Saved with the Client
 * profile.
 * <br>
 * Profiles can be loaded from codes other players share, so treat everything read here as
 * untrusted: check types and ranges before using a value.
 */
public interface GuiThemeState {
    /**
     * @return A copy of the stored state, empty if there is none
     */
    JsonObject read();

    /**
     * Replaces the stored state. Large states are dropped when saving.
     *
     * @param state The new state
     */
    void write(JsonObject state);
}
