package dev.boze.api.client.cape;

/**
 * Enum for cape load results
 *
 */
public enum CapeLoadResult {
    /**
     * Cape loaded successfully
     */
    Success,

    /**
     * Server/website did not respond with a cape texture
     */
    InvalidResponse,

    /**
     * Server/website did not respond at all, or there was an error parsing the response
     */
    Error
}
