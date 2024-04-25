package dev.boze.api.client;

import dev.boze.api.internal.Instances;

/**
 * Friend manager
 */
public final class FriendManager {

    /**
     * @param name Name of friend to check
     * @return true if friend is added, false if friend is not added
     */
    public static boolean isFriend(String name) {
        return Instances.getFriends().isFriend(name);
    }

    /**
     * @param name Name of friend to add
     * @return true if friend was added, false if friend was already added
     */
    public static boolean addFriend(String name) {
        return Instances.getFriends().addFriend(name);
    }

    /**
     * @param friend Friend to delete
     */
    public static void delFriend(String friend) {
        Instances.getFriends().delFriend(friend);
    }
}
