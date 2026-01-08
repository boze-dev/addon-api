package dev.boze.api.event;

import dev.boze.api.render.ClientColor;

import java.util.HashSet;

/**
 * EventShader
 * <br>
 * Fired before shader preparation each frame
 */
public class EventShader {
    private static final EventShader INSTANCE = new EventShader();

    private final HashSet<ClientColor> colors = new HashSet<>();

    private EventShader() {}

    public static EventShader get() {
        INSTANCE.colors.clear();
        return INSTANCE;
    }

    /**
     * Prepare a color for shader rendering
     * <br>
     * You must call this each frame you use the color for shader renders
     * <br>
     * The shader may not render properly if this isn't called
     *
     * @param color Color to prepare
     */
    public void prepare(ClientColor color) {
        colors.add(color);
    }

    /**
     * Called internally to get colors list
     *
     * @return The list of colors to prepare
     */
    public HashSet<ClientColor> getColors() {
        return colors;
    }
}
