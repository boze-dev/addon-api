package dev.boze.api.internal.interfaces;

public interface IInput {
    String getKeyName(int key);

    String getButtonName(int button);

    /** The GLFW code profiles save for a bind, so other versions read it too. */
    int toLegacy(boolean isButton, int bind);

    /** A bind saved as a GLFW code, as a bind. */
    int fromProfile(boolean isButton, int bind);
}
