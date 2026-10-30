package dev.boze.api.gui.render;

/**
 * Corner masks for {@link GuiPainter#roundRect}.
 */
public final class Corners {
    public static final int TOP_LEFT = 1;
    public static final int TOP_RIGHT = 2;
    public static final int BOTTOM_RIGHT = 4;
    public static final int BOTTOM_LEFT = 8;
    public static final int TOP = TOP_LEFT | TOP_RIGHT;
    public static final int BOTTOM = BOTTOM_LEFT | BOTTOM_RIGHT;
    public static final int LEFT = TOP_LEFT | BOTTOM_LEFT;
    public static final int RIGHT = TOP_RIGHT | BOTTOM_RIGHT;
    public static final int ALL = TOP | BOTTOM;

    private Corners() {
    }
}
