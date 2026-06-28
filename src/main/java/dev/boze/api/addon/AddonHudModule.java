package dev.boze.api.addon;

import dev.boze.api.render.HudLine;

import java.util.List;

/**
 * Base class for addon HUD modules.
 * <br>
 * A HUD module supplies its content as a list of {@link HudLine}s (each a row of colored
 * {@link dev.boze.api.render.HudText} segments). Boze renders the lines as a draggable HUD element
 * in the Hud category, so addons don't deal with positioning or text layout themselves - just return
 * what to show. {@link #getLines()} is called every frame while the module is enabled.
 */
public abstract class AddonHudModule extends AddonModule {

    /**
     * Creates a new HUD module.
     *
     * @param name        Internal name of this module
     * @param description Description of what this module shows
     */
    protected AddonHudModule(String name, String description) {
        super(name, description);
    }

    /**
     * Supplies the lines to render, top to bottom. Called every frame while enabled.
     *
     * @return the HUD lines to draw
     */
    public abstract List<HudLine> getLines();

    /**
     * Whether Boze should sort the lines by rendered length, like the ArrayList HUD: longest line
     * toward the nearest top/bottom screen edge. Override to return true; defaults to false.
     *
     * @return true to sort lines by length
     */
    public boolean sortByLength() {
        return false;
    }
}
