package dev.boze.api.gui.render;

/**
 * Silhouettes for {@link GuiPainter#drawOutline} and {@link GuiPainter#pushBackdrop}. Shapes added
 * here are not drawn themselves, and are clipped and transformed like drawing.
 */
public interface GuiMask {
    void rect(double x, double y, double width, double height);

    void roundRect(double x, double y, double width, double height, double radius, int corners);
}
