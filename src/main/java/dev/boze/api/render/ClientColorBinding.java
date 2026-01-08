package dev.boze.api.render;

/**
 * Binding between a client color and a consumer.
 * <br>
 * Implementations are notified when the underlying color becomes unavailable
 * (for example, if it is removed from the global color store).
 */
public interface ClientColorBinding {

    /**
     * Called when the bound color has been deleted.
     * Implementations should fall back to a safe default.
     */
    void onClientColorDeleted();
}
