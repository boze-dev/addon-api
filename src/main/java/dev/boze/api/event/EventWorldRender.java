package dev.boze.api.event;


import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;

/**
 * EventWorldRender
 * <br>
 * Called on world render, used for 3D rendering
 */
public class EventWorldRender {
    private static final EventWorldRender INSTANCE = new EventWorldRender();

    public PoseStack matrices;
    public Camera camera;
    public float tickDelta;

    /**
     * Get the event instance - this is called onm world render by Boze, and should not be called by addons
     *
     * @param matrices MatrixStack
     * @param camera Camera
     * @param tickDelta Tick delta
     * @return The event instance
     */
    public static EventWorldRender get(PoseStack matrices, Camera camera, float tickDelta) {
        INSTANCE.matrices = matrices;
        INSTANCE.camera = camera;
        INSTANCE.tickDelta = tickDelta;
        return INSTANCE;
    }
}
