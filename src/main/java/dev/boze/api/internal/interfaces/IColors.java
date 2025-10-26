package dev.boze.api.internal.interfaces;

import dev.boze.api.render.ClientColor;

public interface IColors {

    ClientColor getColor(String name);

    ClientColor staticColor(String name, int red, int green, int blue);

    ClientColor changingColor(String name, boolean hsb, boolean mirror, float speed, int[] colors);

    ClientColor gradientColor(String name, boolean hsb, boolean mirror, float angle, float scale, float motion, int[] colors);

    ClientColor registerColor(String name, ClientColor color);
}
