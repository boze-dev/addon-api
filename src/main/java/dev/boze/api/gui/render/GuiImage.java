package dev.boze.api.gui.render;

import dev.boze.api.internal.Instances;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

/**
 * A picture for {@link GuiPainter#image}: an item, a GUI sprite, a texture, or a vector image.
 */
public interface GuiImage {
    /**
     * A texture, drawn in order with the rest of the frame: clipped, faded and covered by later
     * drawing like any shape.
     *
     * @param texture Location of a texture, for example {@code myaddon:textures/gui/logo.png}.
     *                Minecraft loads it the first time it is drawn.
     * @return The image
     */
    static GuiImage texture(Identifier texture) {
        return Instances.getGui().textureImage(texture);
    }

    /**
     * A vector image: the filled shapes of an SVG file in one color, sharp at any size. It is drawn
     * like text, in the paint given to
     * {@link GuiPainter#image(GuiImage, double, double, double, double, GuiPaint)} (white
     * otherwise), with the text shadow when asked for, and glows with
     * {@link GuiPainter#pushTextGlow}. Strokes, colors and styles in
     * the file are ignored, and a shape with {@code fill="none"} is left out.
     *
     * @param svg Location of an SVG file, for example {@code myaddon:textures/gui/logo.svg}. It is
     *            read the first time it is drawn.
     * @return The image
     */
    static GuiImage svg(Identifier svg) {
        return Instances.getGui().svgImage(svg);
    }

    /**
     * @param stack The item
     * @return The image, or null for an empty stack
     */
    static GuiImage item(ItemStack stack) {
        return stack == null || stack.isEmpty() ? null : Instances.getGui().itemImage(stack);
    }

    /**
     * @param sprite A GUI atlas sprite, for example a mob effect icon
     * @return The image
     */
    static GuiImage sprite(Identifier sprite) {
        return Instances.getGui().spriteImage(sprite);
    }

    /**
     * Items and sprites are drawn by Minecraft's GUI renderer after everything else in the frame:
     * they show on top of later drawing and ignore {@link GuiPainter#pushAlpha}. Clips still apply,
     * rounded to whole GUI pixels.
     *
     * @return Whether this image is drawn that way
     */
    boolean deferred();
}
