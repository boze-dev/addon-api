package dev.boze.api.event;

/**
 * EventTick
 * <p>
 * Tick event
 * <p>
 * This event should be used for features that need to be called at the start/end of ticks
 *
 */
public class EventTick {

    public static class Pre extends EventTick {

        private static final Pre INSTANCE = new Pre();

        /**
         * Gets the event instance - this is called at the start of a tick by Boze, and should not be called by addons
         *
         * @return The event instance
         */
        public static Pre get() {
            return INSTANCE;
        }
    }

    public static class Post extends EventTick {

        private static final Post INSTANCE = new Post();

        /**
         * Gets the event instance - this is called at the end of a tick by Boze, and should not be called by addons
         *
         * @return The event instance
         */
        public static Post get() {
            return INSTANCE;
        }
    }
}
