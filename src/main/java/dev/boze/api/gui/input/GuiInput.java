package dev.boze.api.gui.input;

import dev.boze.api.utility.input.Bind;

import dev.boze.api.gui.util.Rect;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Input services for an open GUI.
 */
public interface GuiInput {
    /**
     * Sets the cursor for this frame. Each frame starts with {@link GuiCursor#DEFAULT}.
     */
    void setCursor(GuiCursor cursor);

    /**
     * @return Clipboard text, or an empty string
     */
    String clipboard();

    void setClipboard(String text);

    boolean isKeyDown(int key);

    boolean isButtonDown(int button);

    /**
     * Waits for the next key or mouse button and passes it to {@code onBind}. Escape gives a
     * cleared bind ({@code new Bind(false, -1)}). Other input is held back meanwhile.
     */
    void captureBind(Consumer<Bind> onBind);

    /**
     * Like {@link #captureBind(Consumer)}, but only a mouse press inside {@code area} becomes the
     * bind. A press outside it ends the wait and is handled as a normal click.
     *
     * @param area Where mouse presses bind, read at each press
     */
    void captureBind(Consumer<Bind> onBind, Supplier<Rect> area);

    void cancelBindCapture();

    /**
     * @return A bind as Boze shows it, such as {@code R}, {@code Mouse 4} or an empty string for
     * no bind
     */
    String bindName(Bind bind);

    /**
     * Plays the click Boze's own GUI plays when something is pressed.
     */
    void clickSound();

    boolean isCapturingBind();

    /**
     * Sends mouse movement to {@code drag} until the button is released.
     */
    void beginDrag(GuiDrag drag);

    void endDrag();

    boolean isDragging();
}
