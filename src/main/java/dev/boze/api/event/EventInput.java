package dev.boze.api.event;

/**
 * EventInput
 * <br>
 * Event called once a tick
 * <br>
 * Use this event to modify player movement input
 */
public class EventInput {
    private static final EventInput INSTANCE = new EventInput();

    /**
     * Horizontal movement tags
     */
    public boolean forward;
    public boolean backward;
    public boolean left;
    public boolean right;

    /**
     * Jumping and sneaking flags
     */
    public boolean jumping;
    public boolean sneaking;

    /**
     * Gets the event instance - this is called by Boze, and should not be called by addons
     *
     * @param forward Current forward movement
     * @param backward Current backward movement
     * @param left Current leftward movement
     * @param right Current rightward movement
     * @param jumping If the player is jumping
     * @param sneaking IF the player is sneaking
     * @return The event instance
     */
    public static EventInput get(boolean forward, boolean backward, boolean left, boolean right, boolean jumping, boolean sneaking) {
        INSTANCE.forward = forward;
        INSTANCE.backward = backward;
        INSTANCE.left = left;
        INSTANCE.right = right;
        INSTANCE.jumping = jumping;
        INSTANCE.sneaking = sneaking;
        return INSTANCE;
    }
}
