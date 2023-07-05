package dev.boze.api.input;

public interface Bind {

    /**
     *
     * @return GLFW value of the bind
     */
    int getBind();

    /**
     *
     * @return true if bind is a mouse button
     */
    boolean isButton();
}
