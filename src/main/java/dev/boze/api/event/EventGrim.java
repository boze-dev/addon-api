package dev.boze.api.event;

import dev.boze.api.interaction.PlaceInteraction;

/**
 * EventGrim
 * <p>
 * Grim rotation/interaction event
 * <p>
 * This event should be used for 2b2t/GrimAC features
 *
 */
public class EventGrim {

    public static class Rotate extends EventGrim {

        private static final Rotate INSTANCE = new Rotate();

        private boolean set;

        private float yaw;
        private float pitch;

        /**
         * Gets the event instance - this is called once a tick by Boze, and should not be called by addons
         *
         * @param yaw Current yaw
         * @param pitch Current pitch
         * @param set If rotations are already set by Boze
         * @return The event instance
         */
        public static Rotate get(float yaw, float pitch, boolean set) {
            INSTANCE.yaw = yaw;
            INSTANCE.pitch = pitch;
            INSTANCE.set = set;
            return INSTANCE;
        }

        /**
         *
         * @param yaw New yaw
         * @param pitch New pitch
         */
        public void set(float yaw, float pitch) {
            this.yaw = yaw;
            this.pitch = pitch;
            this.set = true;
        }

        /**
         * Addons that wish to have a lower priority than Boze should check this method
         * <p>
         * If this method returns true, the addon should not rotate
         *
         * @return are rotations set
         */
        public boolean isSet() {
            return set;
        }

        /**
         * Gets the yaw
         *
         * @return The yaw
         */
        public float getYaw() {
            return yaw;
        }

        /**
         * Gets the pitch
         *
         * @return The pitch
         */
        public float getPitch() {
            return pitch;
        }
    }

    public static abstract class Interact extends EventGrim {
        private static Interact INSTANCE;

        private boolean dirty;

        /**
         * Gets the event instance - this is called once a tick by Boze, and should not be called by addons
         *
         * @param dirty Whether Boze has already interacted with the server
         * @return The event instance
         */
        public static Interact get(boolean dirty) {
            INSTANCE.dirty = dirty;
            return INSTANCE;
        }

        /**
         * Execute a place interaction
         * <p>
         * This will not rotate - that has to be done on EventGrim.Rotate
         *
         * @param placeInteraction The place interaction to execute
         */
        public abstract void place(PlaceInteraction placeInteraction);

        /**
         * @return Whether Boze has already interacted with the server
         */
        public boolean isDirty() {
            return dirty;
        }
    }

}
