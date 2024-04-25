package dev.boze.api.internal.interfaces;

public interface IFriends {
    boolean isFriend(String name);

    boolean addFriend(String name);

    void delFriend(String friend);
}
