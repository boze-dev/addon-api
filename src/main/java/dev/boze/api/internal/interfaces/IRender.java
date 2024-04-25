package dev.boze.api.internal.interfaces;

import dev.boze.api.render.DrawColor;

public interface IRender {

    DrawColor newColor();

    DrawColor newColor(int packed);

    DrawColor newColor(int r, int g, int b, int a);
}
