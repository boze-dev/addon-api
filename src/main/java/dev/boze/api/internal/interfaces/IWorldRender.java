package dev.boze.api.internal.interfaces;

import dev.boze.api.render.ClientColor;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.math.MatrixStack;

public interface IWorldRender {

    void start();

    void draw(MatrixStack matrices, Framebuffer framebuffer);

    void boxLines(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void boxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void side(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2);

    void shaderBoxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void shaderSide(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2, float fade);
}
