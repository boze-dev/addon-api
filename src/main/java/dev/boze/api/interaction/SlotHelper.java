package dev.boze.api.interaction;

public interface SlotHelper {

    /**
     * Sync client-side hotbar slot with server
     */
    void sync();

    /**
     *
     * @return Current server-side hotbar slot (0-9)
     */
    int currentSlot();
}
