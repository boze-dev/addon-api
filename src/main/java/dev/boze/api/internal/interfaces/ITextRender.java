package dev.boze.api.internal.interfaces;

import dev.boze.api.render.ClientColor;
import dev.boze.api.render.TextType;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ITextRender {

    void start(TextType type, double scale);

    void start(TextType type, double scale, boolean measureMode);

    void draw(GuiGraphicsExtractor context);

    double render(String text, double x, double y, ClientColor color, float opacity, boolean shadow);

    double getWidth(String text, boolean shadow);

    double getHeight(boolean shadow);
}
