package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.interaction.InteractionMode;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public interface IInteraction {
    boolean place(InteractionMode mode, BlockHitResult hitResult, Hand hand);
}
