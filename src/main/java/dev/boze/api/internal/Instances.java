package dev.boze.api.internal;

import dev.boze.api.internal.interfaces.*;

public class Instances {

    private static ICapes capes;
    private static IChat chat;
    private static IFriends friends;
    private static IInput input;
    private static IInteraction interaction;
    private static IJson json;
    private static IModules modules;
    private static IRender render;

    public static ICapes getCapes() {
        return capes;
    }

    public static IChat getChat() {
        return chat;
    }

    public static IFriends getFriends() {
        return friends;
    }

    public static IInput getInput() {
        return input;
    }

    public static IInteraction getInteraction() {
        return interaction;
    }

    public static IJson getJson() {
        return json;
    }

    public static IModules getModules() {
        return modules;
    }

    public static IRender getRender() {
        return render;
    }
}
