package dev.boze.api;

import dev.boze.api.client.ChatHelper;
import dev.boze.api.client.FriendManager;
import dev.boze.api.client.cape.CapesManager;
import dev.boze.api.config.JsonTools;
import dev.boze.api.input.InputNameProvider;
import dev.boze.api.interaction.RotationHelper;
import dev.boze.api.interaction.SlotHelper;
import dev.boze.api.module.ModuleInfoProvider;
import dev.boze.api.render.Drawer3D;
import dev.boze.api.render.LinkedColorProvider;

import java.io.File;

/**
 * Globals class, with instances of various helpers/tools/etc.
 */
public class Globals {
    private static File addonDir;

    private static ChatHelper chatHelper;
    private static RotationHelper rotationHelper;
    private static SlotHelper slotHelper;

    private static ModuleInfoProvider moduleInfoProvider;
    private static InputNameProvider inputNameProvider;
    private static LinkedColorProvider linkedColorProvider;

    private static CapesManager capesManager;
    private static FriendManager friendManager;

    private static JsonTools jsonTools;

    public static File getAddonDir() {
        return addonDir;
    }

    public static ChatHelper getChatHelper() {
        return chatHelper;
    }

    public static RotationHelper getRotationHelper() {
        return rotationHelper;
    }

    public static SlotHelper getSlotHelper() {
        return slotHelper;
    }

    public static ModuleInfoProvider getModuleInfoProvider() {
        return moduleInfoProvider;
    }

    public static InputNameProvider getInputNameProvider() {
        return inputNameProvider;
    }

    public static LinkedColorProvider getLinkedColorProvider() {
        return linkedColorProvider;
    }

    public static CapesManager getCapesManager() {
        return capesManager;
    }

    public static FriendManager getFriendManager() {
        return friendManager;
    }

    public static JsonTools getJsonTools() {
        return jsonTools;
    }
}
