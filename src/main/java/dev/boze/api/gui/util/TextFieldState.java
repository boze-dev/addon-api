package dev.boze.api.gui.util;

import dev.boze.api.gui.input.GuiKeyEvent;
import com.mojang.blaze3d.platform.InputConstants;

import java.util.function.IntPredicate;
import java.util.function.Supplier;

/**
 * Text being typed: the text, a caret, a length limit and the characters the field accepts.
 */
public final class TextFieldState {
    /**
     * Any character that is not a control character.
     */
    public static final IntPredicate PRINTABLE = c -> !Character.isISOControl(c);

    private final int maxLength;
    private final IntPredicate allowed;
    private String text = "";
    private int caret;

    /**
     * @param maxLength Most characters the field holds
     * @param allowed   Which code points can be typed or pasted
     */
    public TextFieldState(int maxLength, IntPredicate allowed) {
        this.maxLength = maxLength;
        this.allowed = allowed;
    }

    public String text() {
        return text;
    }

    public boolean isEmpty() {
        return text.isEmpty();
    }

    public int caret() {
        return caret;
    }

    public int maxLength() {
        return maxLength;
    }

    /**
     * Replaces the text without filtering it, with the caret at the end.
     */
    public void setText(String text) {
        this.text = text == null ? "" : text;
        caret = this.text.length();
    }

    public void clear() {
        setText("");
    }

    /**
     * Inserts what the field accepts at the caret, up to its length limit.
     *
     * @return Whether anything was inserted
     */
    public boolean insert(String typed) {
        StringBuilder accepted = new StringBuilder();
        int room = maxLength - text.length();
        for (int i = 0; i < typed.length() && accepted.length() < room; ) {
            int codepoint = typed.codePointAt(i);
            i += Character.charCount(codepoint);
            if (allowed.test(codepoint) && accepted.length() + Character.charCount(codepoint) <= room) {
                accepted.appendCodePoint(codepoint);
            }
        }
        if (accepted.isEmpty()) return false;
        text = text.substring(0, caret) + accepted + text.substring(caret);
        caret += accepted.length();
        return true;
    }

    public boolean insert(int codepoint) {
        return insert(Character.toString(codepoint));
    }

    public void backspace() {
        if (caret == 0) return;
        int start = text.offsetByCodePoints(caret, -1);
        text = text.substring(0, start) + text.substring(caret);
        caret = start;
    }

    public void delete() {
        if (caret >= text.length()) return;
        int end = text.offsetByCodePoints(caret, 1);
        text = text.substring(0, caret) + text.substring(end);
    }

    public void left() {
        if (caret > 0) caret = text.offsetByCodePoints(caret, -1);
    }

    public void right() {
        if (caret < text.length()) caret = text.offsetByCodePoints(caret, 1);
    }

    public void home() {
        caret = 0;
    }

    public void end() {
        caret = text.length();
    }

    /**
     * Handles the editing keys every Boze text field takes: Backspace (with Ctrl or Cmd, everything
     * before the caret), Delete, Left, Right, Home, End and paste.
     *
     * @param clipboard Gives the clipboard text when pasting
     * @return Whether the key was used
     */
    public boolean keyPressed(GuiKeyEvent event, Supplier<String> clipboard) {
        switch (event.key()) {
            case InputConstants.KEY_BACKSPACE -> {
                if (event.hasActionModifier()) {
                    text = afterCaret();
                    caret = 0;
                } else {
                    backspace();
                }
                return true;
            }
            case InputConstants.KEY_DELETE -> delete();
            case InputConstants.KEY_LEFT -> left();
            case InputConstants.KEY_RIGHT -> right();
            case InputConstants.KEY_HOME -> home();
            case InputConstants.KEY_END -> end();
            default -> {
                if (!event.isPaste()) return false;
                String pasted = clipboard == null ? null : clipboard.get();
                if (pasted != null && !pasted.isEmpty()) insert(pasted);
            }
        }
        return true;
    }

    /**
     * @return Text before the caret
     */
    public String beforeCaret() {
        return text.substring(0, caret);
    }

    /**
     * @return Text after the caret
     */
    public String afterCaret() {
        return text.substring(caret);
    }
}
