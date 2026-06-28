package dev.boze.api.internal.interfaces;

import dev.boze.api.render.ClientColor;

public interface IChat {
    void sendMsg(String title, String message);

    void sendWarning(String title, String warning);

    void sendError(String title, String error);

    void sendMsg(String brand, ClientColor brandColor, String title, String message);

    void sendWarning(String brand, ClientColor brandColor, String title, String warning);

    void sendError(String brand, ClientColor brandColor, String title, String error);

    String getCommandPrefix();
}
