package dev.boze.api.internal.interfaces;

import java.util.List;

public interface IFriends {
    boolean isFriend(String name);

    boolean addFriend(String name);

    void delFriend(String friend);

    List<String> getFriends();
}
