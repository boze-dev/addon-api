package dev.boze.api.gui.input;

/**
 * A typed character.
 *
 * @param codepoint Unicode code point
 */
public record GuiCharEvent(int codepoint) {
    public String text() {
        return Character.toString(codepoint);
    }

    /**
     * @return The character, or {@code '\0'} outside the basic multilingual plane
     */
    public char character() {
        return Character.isBmpCodePoint(codepoint) ? (char) codepoint : '\0';
    }
}
