package dev.boze.api.internal.interfaces;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec3d;

public interface IBillboard {

    boolean start(Vec3d worldPosition, DrawContext drawContext, double factor, double minScale, double maxScale);

    boolean start(Vec3d worldPosition, DrawContext drawContext, double scale);

    void stop(DrawContext drawContext);
}
