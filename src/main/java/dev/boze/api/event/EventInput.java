package dev.boze.api.event;

/**
 * EventInput
 * <p>
 * Event called once a tick after input polling
 * <p>
 * Use this event to modify player movement input
 */
public class EventInput {
    private static final EventInput INSTANCE = new EventInput();

    /**
     * Sideways and forwards movement
     * -1F to 1F
     */
    public float movementSideways;
    public float movementForward;

    /**
     * Jumping and sneaking flags
     */
    public boolean jumping;
    public boolean sneaking;

    /**
     * Gets the event instance - this is called by Boze, and should not be called by addons
     *
     * @param movementSideways Current sideways movement
     * @param movementForward Current forwards movement
     * @param jumping If the player is jumping
     * @param sneaking IF the player is sneaking
     * @return The event instance
     */
    public static EventInput get(float movementSideways, float movementForward, boolean jumping, boolean sneaking) {
        INSTANCE.movementSideways = movementSideways;
        INSTANCE.movementForward = movementForward;
        INSTANCE.jumping = jumping;
        INSTANCE.sneaking = sneaking;
        return INSTANCE;
    }
}
