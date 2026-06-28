package dev.boze.api.render;

/**
 * Controls how the original (un-blurred) text is shown under a bloom region
 * ({@link TextDrawer#bloomStart(int, float, BloomMode)}).
 */
public enum BloomMode {
    /**
     * Only the bloom is drawn; the original text is not.
     */
    Off,

    /**
     * The original text plus bloom only around it (outside the text).
     */
    Keep,

    /**
     * The original text plus bloom everywhere (brighter).
     */
    Add
}
