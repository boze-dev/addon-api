package dev.boze.api.utility.interaction;

/**
 * Toggleable swap type for item swapping with off option
 */
public enum ToggleableSwapType {
    /**
     * No swapping
     */
    Off(null),

    /**
     * Normal swap mode
     */
    Normal(SwapType.Normal),

    /**
     * Silent swap mode
     */
    Silent(SwapType.Silent),

    /**
     * Alternative swap mode
     */
    Alt(SwapType.Alt);

    public final SwapType swapType;

    ToggleableSwapType(SwapType swapType) {
        this.swapType = swapType;
    }
}
