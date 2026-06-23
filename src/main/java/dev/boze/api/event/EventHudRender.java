package dev.boze.api.event;

import net.minecraft.client.gui.GuiGraphicsExtractor;

/**
 * Event called when the HUD is being rendered
 * <br>
 * Used for 2D rendering
 */
public class EventHudRender {
    private static final EventHudRender INSTANCE = new EventHudRender();

    /**
     * The current draw context
     */
    public GuiGraphicsExtractor context;

    /**
     * The current tick delta
     */
    public float tickDelta;

    /**
     * Gets the event instance - this is called when the HUD is being rendered by Boze, and should not be called by addons
     *
     * @param context The current draw context
     * @param tickDelta The current tick delta
     * @return The event instance
     */
    public static EventHudRender get(GuiGraphicsExtractor context, float tickDelta) {
        INSTANCE.context = context;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
