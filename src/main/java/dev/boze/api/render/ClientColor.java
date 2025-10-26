package dev.boze.api.render;

/**
 * Represents a color provided by the client.
 * <p></p>
 * Concrete instances wrap client-side color implementations.
 */
public abstract class ClientColor {

    /**
     * @return Identifier for this color.
     * <p></p>
     * Registered colors return their entry name. Defaults use {@code _default} for static/changing
     * selections and {@code _default_<gradient id>} for gradients.
     */
    public abstract String getIdentifier();

    /**
     * @return Lightweight copy referencing the same underlying client color
     */
    public abstract ClientColor copy();

    /**
     * Binds the given consumer to this color. The concrete implementation is
     * responsible for forwarding the binding to the underlying color system.
     *
     * @param binding Binding owner that should be notified about deletions
     */
    public abstract void choose(ClientColorBinding binding);

    /**
     * Removes the given consumer binding from this color.
     *
     * @param binding Binding owner
     */
    public abstract void unchoose(ClientColorBinding binding);

    /**
     * Deletes the underlying color if possible. Implementations should follow
     * the same semantics as the client color system.
     */
    public abstract void delete();
}
