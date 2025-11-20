package dev.boze.api.internal;

import dev.boze.api.internal.interfaces.*;

public class Instances {

    private static ICapes capes;
    private static IChat chat;
    private static IEntities entities;
    private static IFriends friends;
    private static IMath math;
    private static IWorld world;
    private static IWorldRender worldRender;
    private static IHudRender hudRender;
    private static ITextRender textRender;
    private static IBillboard billboard;
    private static IInput input;
    private static IInteraction interaction;
    private static IInventory inventory;
    private static IJson json;
    private static IModules modules;
    private static IRayCasting rayCasting;
    private static IAutoMine autoMine;
    private static IAutoCrystal autoCrystal;
    private static IProfiles profiles;
    private static IColors colors;

    public static ICapes getCapes() {
        return capes;
    }

    public static IChat getChat() {
        return chat;
    }

    public static IEntities getEntities() {
        return entities;
    }

    public static IFriends getFriends() {
        return friends;
    }

    public static IMath getMath() {
        return math;
    }

    public static IWorld getWorld() {
        return world;
    }

    public static IWorldRender getWorldRender() {
        return worldRender;
    }

    public static IHudRender getHudRender() {
        return hudRender;
    }

    public static ITextRender getTextRender() {
        return textRender;
    }

    public static IBillboard getBillboard() {
        return billboard;
    }

    public static IInput getInput() {
        return input;
    }

    public static IInteraction getInteraction() {
        return interaction;
    }

    public static IInventory getInventory() {
        return inventory;
    }

    public static IJson getJson() {
        return json;
    }

    public static IModules getModules() {
        return modules;
    }

    public static IRayCasting getRayCasting() {
        return rayCasting;
    }

    public static IAutoMine getAutoMine() {
        return autoMine;
    }

    public static IAutoCrystal getAutoCrystal() {
        return autoCrystal;
    }

    public static IProfiles getProfiles() {
        return profiles;
    }

    public static IColors getColors() {
        return colors;
    }
}
