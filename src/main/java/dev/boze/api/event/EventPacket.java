package dev.boze.api.event;

import net.minecraft.network.protocol.Packet;

/**
 * EventPacket
 * <br>
 * Packet send/receive events
 */
public abstract class EventPacket extends CancellableEvent {
    /**
     * Gets the packet associated with this event
     *
     * @return The packet being sent or received
     */
    public abstract Packet<?> getPacket();

    public static class Send extends EventPacket {
        /**
         * The packet being sent
         */
        public final Packet<?> packet;

        public Send(Packet<?> packet) {
            this.packet = packet;
        }

        @Override
        public Packet<?> getPacket() {
            return packet;
        }
    }

    public static class Receive extends EventPacket {
        /**
         * The packet being received
         */
        public final Packet<?> packet;

        public Receive(Packet<?> packet) {
            this.packet = packet;
        }

        @Override
        public Packet<?> getPacket() {
            return packet;
        }
    }
}
