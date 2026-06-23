package dev.boze.api.internal.interfaces;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.boze.api.render.ClientColor;

public interface IWorldRender {

    void start();

    void draw(PoseStack matrices, RenderTarget framebuffer);

    void boxLines(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void boxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void side(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2);

    void shaderBoxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade);

    void shaderSide(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2, float fade);
}
