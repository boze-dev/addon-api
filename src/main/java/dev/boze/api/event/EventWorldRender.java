package dev.boze.api.event;


import dev.boze.api.render.Drawer3D;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;

/**
 * EventWorldRender
 * <p>
 * Called on world render, used for 3D rendering
 */
public class EventWorldRender {
    private static final EventWorldRender INSTANCE = new EventWorldRender();

    /**
     * 3D Drawer instance
     */
    public Drawer3D drawer;

    public MatrixStack matrices;
    public Camera camera;
    public float tickDelta;

    /**
     * Get the event instance - this is called onm world render by Boze, and should not be called by addons
     *
     * @param drawer 3D Drawer
     * @param matrices MatrixStack
     * @param camera Camera
     * @param tickDelta Tick delta
     * @return The event instance
     */
    public static EventWorldRender get(Drawer3D drawer, MatrixStack matrices, Camera camera, float tickDelta) {
        INSTANCE.drawer = drawer;
        INSTANCE.matrices = matrices;
        INSTANCE.camera = camera;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
