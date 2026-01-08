package dev.boze.api.utility;

import dev.boze.api.internal.Instances;

/**
 * A helper for sending messages to the client chat
 * <br>
 * This only sends messages client-side, it does not send messages to the server!
 */
public final class ChatHelper {

    /**
     * Sends a message to the chat
     *
     * @param message The message
     */
    public static void sendMsg(String message) {
        Instances.getChat().sendMsg(message);
    }

    /**
     * Sends a message to the chat with a title
     * <br>
     * It's recommended to use this method when sending messages from a command/module
     *
     * @param title The title of the message/the command/module name
     * @param message The message
     */
    public static void sendMsg(String title, String message) {
        Instances.getChat().sendMsg(title, message);
    }

    /**
     * Sends a warning to the chat
     *
     * @param warning The warning
     */
    public static void sendWarning(String warning) {
        Instances.getChat().sendWarning(warning);
    }

    /**
     * Sends a warning to the chat with a title
     * <br>
     * It's recommended to use this method when sending warnings from a command/module
     *
     * @param title The title of the warning/the command/module name
     * @param warning The warning
     */
    public static void sendWarning(String title, String warning) {
        Instances.getChat().sendWarning(title, warning);
    }

    /**
     * Sends an error to the chat
     *
     * @param error The error
     */
    public static void sendError(String error) {
        Instances.getChat().sendError(error);
    }

    /**
     * Sends an error to the chat with a title
     * <br>
     * It's recommended to use this method when sending errors from a command/module
     *
     * @param title The title of the error/the command/module name
     * @param error The error
     */
    public static void sendError(String title, String error) {
        Instances.getChat().sendError(title, error);
    }

    /**
     * Gets the client command prefix
     * @return command prefix string
     */
    public static String getCommandPrefix() {
        return Instances.getChat().getCommandPrefix();
    }
}
