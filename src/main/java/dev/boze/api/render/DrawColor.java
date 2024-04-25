package dev.boze.api.render;

import dev.boze.api.config.Serializable;
import dev.boze.api.internal.Instances;

/**
 * DrawColor
 *
 * This is used for specifying colors for rendering
 *
 */
public interface DrawColor extends Serializable<DrawColor> {

    static DrawColor create() {
        return Instances.getRender().newColor();
    }

    static DrawColor create(int rgba) {
        return Instances.getRender().newColor(rgba);
    }

    static DrawColor create(int r, int g, int b, int a) {
        return Instances.getRender().newColor(r, g, b, a);
    }

    void setRGBA(int r, int g, int b, int a);

    int getRGBA();

    int getR();

    int getG();

    int getB();

    int getA();

    void setR(int r);

    void setG(int g);

    void setB(int b);

    void setA(int a);

    // "Rainbow"/Gradient color settings - don't use directly unless you know what you're doing

    double getSpeed();

    void setSpeed(double speed);

    double getHueOffset();

    void setHueOffset(double hueOffset);

    double getGradientX();

    void setGradientX(double gradientX);

    double getGradientY();

    void setGradientY(double gradientY);

    double getMinHue();

    void setMinHue(double minHue);

    double getMaxHue();

    void setMaxHue(double maxHue);

    /**
     * Clone this color
     *
     * @return a clone of this color
     */
    DrawColor clone();
}
