package dev.boze.api.render;

/**
 * LinkedColorProvider
 *
 * Creates a new Boze color with a LinkedColor interface
 *
 */
public interface LinkedColorProvider {

    /**
     * Create a new LinkedColor object
     *
     * @return a new LinkedColor object
     */
    LinkedColor newColor();

    /**
     * Create a new LinkedColor object
     *
     * @param packed the packed color
     * @return a new LinkedColor object
     */
    LinkedColor newColor(int packed);

    /**
     * Create a new LinkedColor object
     *
     * @param r the red value
     * @param g the green value
     * @param b the blue value
     * @param a the alpha value
     * @return a new LinkedColor object
     */
    LinkedColor newColor(int r, int g, int b, int a);
}
