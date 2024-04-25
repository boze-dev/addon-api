package dev.boze.api.internal.interfaces;

import dev.boze.api.interaction.Rotation;
import net.minecraft.util.math.Vec3d;

public interface IInteraction {
    Rotation calculateAngle(Vec3d to);

    Rotation calculateAngle(Vec3d from, Vec3d to);

    void sync();

    int currentSlot();
}
