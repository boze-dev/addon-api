package dev.boze.api.event;

/**
 * Base class for cancellable events
 * <br>
 * Cancellable events can be cancelled by addons to prevent the default behavior.
 * Other addons can check if an event is cancelled to modify their behavior accordingly.
 */
public abstract class CancellableEvent {
    private boolean cancelled = false;

    /**
     * Cancels this event, preventing the default behavior
     */
    public void cancel() {
        cancelled = true;
    }

    /**
     * Sets whether this event is cancelled
     *
     * @param cancelled Whether the event should be cancelled
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns whether this event has been cancelled
     *
     * @return true if the event is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return cancelled;
    }
}
