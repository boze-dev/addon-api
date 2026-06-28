package dev.boze.api.utility;

import dev.boze.api.internal.Instances;
import dev.boze.api.render.ClientColor;

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
        Instances.getChat().sendMsg(null, message);
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
     * Sends a message to the chat with a custom brand prefix in place of the default [Boze]
     * <br>
     * Output is {@code [brand] [title] message} with the brand shown in the boze color
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param title The title of the message/the command/module name
     * @param message The message
     */
    public static void sendMsg(String brand, String title, String message) {
        Instances.getChat().sendMsg(brand, null, title, message);
    }

    /**
     * Sends a message to the chat with a custom brand prefix and color in place of the default [Boze]
     * <br>
     * Output is {@code [brand] [title] message} with the brand shown in the given color
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param title The title of the message/the command/module name
     * @param message The message
     */
    public static void sendMsg(String brand, ClientColor brandColor, String title, String message) {
        Instances.getChat().sendMsg(brand, brandColor, title, message);
    }

    /**
     * Sends a message to the chat with a custom brand prefix and color, without a title
     * <br>
     * Output is {@code [brand] message}
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param message The message
     */
    public static void sendMsg(String brand, ClientColor brandColor, String message) {
        Instances.getChat().sendMsg(brand, brandColor, null, message);
    }

    /**
     * Sends a warning to the chat
     *
     * @param warning The warning
     */
    public static void sendWarning(String warning) {
        Instances.getChat().sendWarning(null, warning);
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
     * Sends a warning to the chat with a custom brand prefix in place of the default [Boze]
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param title The title of the warning/the command/module name
     * @param warning The warning
     */
    public static void sendWarning(String brand, String title, String warning) {
        Instances.getChat().sendWarning(brand, null, title, warning);
    }

    /**
     * Sends a warning to the chat with a custom brand prefix and color in place of the default [Boze]
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param title The title of the warning/the command/module name
     * @param warning The warning
     */
    public static void sendWarning(String brand, ClientColor brandColor, String title, String warning) {
        Instances.getChat().sendWarning(brand, brandColor, title, warning);
    }

    /**
     * Sends a warning to the chat with a custom brand prefix and color, without a title
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param warning The warning
     */
    public static void sendWarning(String brand, ClientColor brandColor, String warning) {
        Instances.getChat().sendWarning(brand, brandColor, null, warning);
    }

    /**
     * Sends an error to the chat
     *
     * @param error The error
     */
    public static void sendError(String error) {
        Instances.getChat().sendError(null, error);
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
     * Sends an error to the chat with a custom brand prefix in place of the default [Boze]
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param title The title of the error/the command/module name
     * @param error The error
     */
    public static void sendError(String brand, String title, String error) {
        Instances.getChat().sendError(brand, null, title, error);
    }

    /**
     * Sends an error to the chat with a custom brand prefix and color in place of the default [Boze]
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param title The title of the error/the command/module name
     * @param error The error
     */
    public static void sendError(String brand, ClientColor brandColor, String title, String error) {
        Instances.getChat().sendError(brand, brandColor, title, error);
    }

    /**
     * Sends an error to the chat with a custom brand prefix and color, without a title
     *
     * @param brand The brand prefix text (shown in place of [Boze])
     * @param brandColor The color of the brand prefix (null = boze color). Gradient colors are NOT
     *                   supported here and will render incorrectly; use a static or changing color.
     * @param error The error
     */
    public static void sendError(String brand, ClientColor brandColor, String error) {
        Instances.getChat().sendError(brand, brandColor, null, error);
    }

    /**
     * Gets the client command prefix
     * @return command prefix string
     */
    public static String getCommandPrefix() {
        return Instances.getChat().getCommandPrefix();
    }
}
