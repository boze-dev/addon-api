package dev.boze.api.internal.interfaces;

public interface IChat {
    void sendMsg(String message);

    void sendMsg(String title, String message);

    void sendWarning(String warning);

    void sendWarning(String title, String warning);

    void sendError(String error);

    void sendError(String title, String error);
}
