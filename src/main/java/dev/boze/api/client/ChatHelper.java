package dev.boze.api.client;

import dev.boze.api.internal.Instances;

/**
 * A helper for sending messages to the client chat
 * <p>
 * This only sends messages client-side, it does not send messages to the server!
 */
public final class ChatHelper {

    /**
     * Send a message to the chat
     *
     * @param message The message
     */
    public static void sendMsg(String message) {
        Instances.getChat().sendMsg(message);
    }

    /**
     * Send a message to the chat with a title
     * <p>
     * It's recommended to use this method when sending messages from a module
     *
     * @param title The title of the message/the module name
     * @param message The message
     */
    public static void sendMsg(String title, String message) {
        Instances.getChat().sendMsg(title, message);
    }

    /**
     * Send a warning to the chat
     *
     * @param warning The warning
     */
    public static void sendWarning(String warning) {
        Instances.getChat().sendWarning(warning);
    }

    /**
     * Send a warning to the chat with a title
     * <p>
     * It's recommended to use this method when sending warnings from a module
     *
     * @param title The title of the warning/the module name
     * @param warning The warning
     */
    public static void sendWarning(String title, String warning) {
        Instances.getChat().sendWarning(title, warning);
    }

    /**
     * Send an error to the chat
     *
     * @param error The error
     */
    public static void sendError(String error) {
        Instances.getChat().sendError(error);
    }

    /**
     * Send an error to the chat with a title
     * <p>
     * It's recommended to use this method when sending errors from a module
     *
     * @param title The title of the error/the module name
     * @param error The error
     */
    public static void sendError(String title, String error) {
        Instances.getChat().sendError(title, error);
    }
}
