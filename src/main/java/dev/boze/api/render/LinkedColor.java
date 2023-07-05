package dev.boze.api.render;

import dev.boze.api.config.Serializable;

/**
 * LinkedColor
 *
 * This object is linked to an internal Boze color.
 *
 * Apart from the RGBA vals, it cant be used to modify the color, only to represent it (for rendering, in GUI, etc.)
 *
 */
public interface LinkedColor extends Serializable<LinkedColor> {

    void setRGBA(int r, int g, int b, int a);

    int getR();

    int getG();

    int getB();

    int getA();

    void setR(int r);

    void setG(int g);

    void setB(int b);

    void setA(int a);

    /**
     * Clone this color
     *
     * @return a clone of this color
     */
    LinkedColor clone();
}
