package dev.boze.api.utility.interaction;

import dev.boze.api.internal.Instances;
import java.util.function.Predicate;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

/**
 * InvHelper provides utilities for finding items in the player's inventory
 * <br>
 * <strong>WARNING:</strong> Always swap back after swapping with silent/alt mode
 */
public class InvHelper {

    /**
     * Find the first slot containing an item in the entire inventory
     *
     * @param item The item to find
     * @return The slot index, or -1 if not found
     */
    public static int find(Item item) {
        return Instances.getInventory().find(stack -> stack.getItem() == item);
    }

    /**
     * Find the first slot containing a block in the entire inventory
     *
     * @param block The block to find
     * @return The slot index, or -1 if not found
     */
    public static int find(Block block) {
        return Instances.getInventory().find(stack -> stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() == block);
    }

    /**
     * Find the first slot containing any of the specified items in the entire inventory
     *
     * @param items The items to find
     * @return The slot index, or -1 if not found
     */
    public static int find(Item... items) {
        return Instances.getInventory().find(stack -> {
            for (Item item : items) {
                if (stack.getItem() == item) return true;
            }
            return false;
        });
    }

    /**
     * Find the first slot containing any of the specified blocks in the entire inventory
     *
     * @param blocks The blocks to find
     * @return The slot index, or -1 if not found
     */
    public static int find(Block... blocks) {
        return Instances.getInventory().find(stack -> {
            for (Block block : blocks) {
                if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() == block) return true;
            }
            return false;
        });
    }

    /**
     * Find the first slot matching the predicate in the entire inventory
     *
     * @param test The predicate to test item stacks
     * @return The slot index, or -1 if not found
     */
    public static int find(Predicate<ItemStack> test) {
        return Instances.getInventory().find(test);
    }

    /**
     * Find the first slot containing an item in the hotbar
     *
     * @param item The item to find
     * @return The slot index (0-8), or -1 if not found
     */
    public static int findInHotbar(Item item) {
        return Instances.getInventory().findInHotbar(stack -> stack.getItem() == item);
    }

    /**
     * Find the first slot containing a block in the hotbar
     *
     * @param block The block to find
     * @return The slot index (0-8), or -1 if not found
     */
    public static int findInHotbar(Block block) {
        return Instances.getInventory().findInHotbar(stack -> stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() == block);
    }

    /**
     * Find the first slot containing any of the specified items in the hotbar
     *
     * @param items The items to find
     * @return The slot index (0-8), or -1 if not found
     */
    public static int findInHotbar(Item... items) {
        return Instances.getInventory().findInHotbar(stack -> {
            for (Item item : items) {
                if (stack.getItem() == item) return true;
            }
            return false;
        });
    }

    /**
     * Find the first slot containing any of the specified blocks in the hotbar
     *
     * @param blocks The blocks to find
     * @return The slot index (0-8), or -1 if not found
     */
    public static int findInHotbar(Block... blocks) {
        return Instances.getInventory().findInHotbar(stack -> {
            for (Block block : blocks) {
                if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() == block) return true;
            }
            return false;
        });
    }

    /**
     * Find the first slot matching the predicate in the hotbar
     *
     * @param test The predicate to test item stacks
     * @return The slot index (0-8), or -1 if not found
     */
    public static int findInHotbar(Predicate<ItemStack> test) {
        return Instances.getInventory().findInHotbar(test);
    }

    /**
     * Swap to the specified slot using the given swap type
     *
     * @param slot The slot to swap to
     * @param swapType The swap type to use
     * @return true if swap was successful
     */
    public static boolean swapToSlot(int slot, SwapType swapType) {
        return Instances.getInventory().swapToSlot(slot, swapType);
    }

    /**
     * Swap to the specified slot using the given toggleable swap type
     *
     * @param slot The slot to swap to
     * @param swapType The toggleable swap type to use
     * @return true if swap was successful, false if Off or swap failed
     */
    public static boolean swapToSlot(int slot, ToggleableSwapType swapType) {
        if (swapType.swapType == null) {
            return false;
        }

        return Instances.getInventory().swapToSlot(slot, swapType.swapType);
    }

    /**
     * Swap back to the original slot
     */
    public static void swapBack() {
        Instances.getInventory().swapBack();
    }
}
