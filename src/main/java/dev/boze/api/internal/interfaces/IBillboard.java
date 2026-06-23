package dev.boze.api.internal.interfaces;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.phys.Vec3;

public interface IBillboard {

    boolean start(Vec3 worldPosition, GuiGraphicsExtractor drawContext, double factor, double minScale, double maxScale);

    boolean start(Vec3 worldPosition, GuiGraphicsExtractor drawContext, double scale);

    void stop(GuiGraphicsExtractor drawContext);
}
