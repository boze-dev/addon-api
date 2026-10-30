package dev.boze.api.gui.render;

import dev.boze.api.internal.Instances;
import net.minecraft.resources.Identifier;

/**
 * Fonts for {@link GuiPainter#setFont}.
 */
public final class GuiFonts {
    private GuiFonts() {
    }

    /**
     * @return The font the user picked for the GUI in Boze's font settings
     */
    public static GuiFont gui() {
        return Instances.getGui().guiFont();
    }

    /**
     * @return Minecraft's font
     */
    public static GuiFont vanilla() {
        return Instances.getGui().vanillaFont();
    }

    /**
     * Loads a TrueType font from resources. Fonts are cached by id.
     *
     * @param ttf Location of a .ttf file, for example {@code myaddon:fonts/inter.ttf}
     * @return The font, or null if it cannot be read
     */
    public static GuiFont load(Identifier ttf) {
        return Instances.getGui().loadFont(ttf);
    }
}
