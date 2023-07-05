package dev.boze.api.client;

/**
 * Friend manager interface
 */
public interface FriendManager {

    /**
     * @param name Name of friend to check
     * @return true if friend is added, false if friend is not added
     */
    boolean isFriend(String name);

    /**
     * @param name Name of friend to add
     * @return true if friend was added, false if friend was already added
     */
    boolean addFriend(String name);

    /**
     * @param friend Friend to delete
     */
    void delFriend(String friend);
}
