package dev.boze.api.internal.interfaces;

import dev.boze.api.utility.interaction.SwapType;
import java.util.function.Predicate;
import net.minecraft.world.item.ItemStack;

public interface IInventory {
    int find(Predicate<ItemStack> test);

    int findInHotbar(Predicate<ItemStack> test);

    boolean swapToSlot(int slot, SwapType swapType);

    void swapBack();
}
