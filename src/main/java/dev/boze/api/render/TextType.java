package dev.boze.api.render;

/**
 * Text rendering type
 * <br>
 * Defines different contexts for text rendering with appropriate font settings
 */
public enum TextType {
    /**
     * GUI text rendering - for menus and interfaces
     */
    GUI,

    /**
     * HUD text rendering - for heads-up display elements
     */
    HUD,

    /**
     * World text rendering - for text in the 3D world space
     */
    WORLD
}
