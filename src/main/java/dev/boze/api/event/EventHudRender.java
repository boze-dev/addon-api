package dev.boze.api.event;


import dev.boze.api.render.Drawer2D;

public class EventHudRender {
    private static final EventHudRender INSTANCE = new EventHudRender();

    public Drawer2D drawer;

    public float tickDelta;

    public static EventHudRender get(Drawer2D drawer, float tickDelta) {
        INSTANCE.drawer = drawer;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
