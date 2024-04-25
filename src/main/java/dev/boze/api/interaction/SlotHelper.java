package dev.boze.api.interaction;

import dev.boze.api.internal.Instances;

/**
 * SlotHelper, for checking and syncing hotbar slot between client and server
 */
public final class SlotHelper {

    /**
     * Sync client-side hotbar slot with server
     */
    public static void sync() {
        Instances.getInteraction().sync();
    }

    /**
     * Get current server-side hotbar slot
     *
     * @return Current server-side hotbar slot (0-9)
     */
    public static int currentSlot() {
        return Instances.getInteraction().currentSlot();
    }
}
