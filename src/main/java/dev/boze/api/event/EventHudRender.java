package dev.boze.api.event;


import dev.boze.api.render.Drawer2D;
import dev.boze.api.render.DrawerText;
import net.minecraft.client.gui.DrawContext;

/**
 * Event called when the HUD is being rendered
 * <p>
 * Used for 2D rendering
 */
public class EventHudRender {
    private static final EventHudRender INSTANCE = new EventHudRender();

    /**
     * The current draw context
     */
    public DrawContext context;

    /**
     * The drawer for 2d rendering
     */
    public Drawer2D drawer;
    /**
     * The drawer for text rendering
     */
    public DrawerText text;

    /**
     * The current tick delta
     */
    public float tickDelta;

    /**
     * Gets the event instance - this is called when the HUD is being rendered by Boze, and should not be called by addons
     *
     * @param context The current draw context
     * @param drawer The drawer for 2d rendering
     * @param text The drawer for text rendering
     * @param tickDelta The current tick delta
     * @return The event instance
     */
    public static EventHudRender get(DrawContext context, Drawer2D drawer, DrawerText text, float tickDelta) {
        INSTANCE.context = context;
        INSTANCE.drawer = drawer;
        INSTANCE.text = text;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
