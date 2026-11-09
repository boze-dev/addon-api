package dev.boze.api.gui.hud;

/**
 * An element's attachment to another element, in GUI-scaled pixels. While the target is off or
 * missing, the element stays at its own screen anchor and keeps the link for when the target returns.
 *
 * @param target The target's title, or its stored name while it is missing
 * @param active Whether the element is following the target right now
 * @param fromX  The attached point of this element
 * @param fromY  The attached point of this element
 * @param toX    The point it is attached to, the same as {@code fromX} while not active
 * @param toY    The point it is attached to, the same as {@code fromY} while not active
 */
public record HudLink(String target, boolean active, double fromX, double fromY, double toX, double toY) {
}
