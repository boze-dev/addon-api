package dev.boze.api.exception;

@Deprecated
public class AddonInitializationException extends Exception {
    public AddonInitializationException(String message) {
        super(message);
    }

    public AddonInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
