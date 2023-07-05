package dev.boze.api.event;


import dev.boze.api.render.Drawer2D;
import net.minecraft.client.gui.DrawContext;

public class EventHudRender {
    private static final EventHudRender INSTANCE = new EventHudRender();

    public DrawContext context;

    public Drawer2D drawer;

    public float tickDelta;

    public static EventHudRender get(DrawContext context, Drawer2D drawer, float tickDelta) {
        INSTANCE.context = context;
        INSTANCE.drawer = drawer;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
