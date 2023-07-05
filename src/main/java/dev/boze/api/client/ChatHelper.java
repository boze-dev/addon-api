package dev.boze.api.client;

/**
 * A helper for sending messages to the client chat
 *
 * This only sends messages client-side, it does not send messages to the server!
 */
public interface ChatHelper {

    /**
     * Send a message to the chat
     *
     * @param message The message
     */
    void sendMsg(String message);

    /**
     * Send a message to the chat with a title
     *
     * It's recommended to use this method when sending messages from a module
     *
     * @param title The title of the message/the module name
     * @param message The message
     */
    void sendMsg(String title, String message);

    /**
     * Send a warning to the chat
     *
     * @param warning The warning
     */
    void sendWarning(String warning);

    /**
     * Send a warning to the chat with a title
     *
     * It's recommended to use this method when sending warnings from a module
     *
     * @param title The title of the warning/the module name
     * @param warning The warning
     */
    void sendWarning(String title, String warning);

    /**
     * Send an error to the chat
     *
     * @param error The error
     */
    void sendError(String error);

    /**
     * Send an error to the chat with a title
     *
     * It's recommended to use this method when sending errors from a module
     *
     * @param title The title of the error/the module name
     * @param error The error
     */
    void sendError(String title, String error);
}
