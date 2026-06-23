package dev.boze.api.render;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.boze.api.internal.Instances;
import dev.boze.api.option.ColorOption;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.AABB;

/**
 * World rendering API for drawing 3D boxes in the world
 * <br>
 * Provides methods for rendering boxes in 3D world space
 * <br>
 * All drawing operations must be wrapped between {@link #start()} and {@link #draw(PoseStack, RenderTarget)} calls
 * <br>
 * You can, of course, draw more than one box per start/draw call
 * <br>
 * See {@link dev.boze.api.event.EventShader} before using shader renders
 * <br>
 * Shader renders have their own fill/outline opacity, defined in Shader module.
 * Outline opacity is used as a scale factor for the shader fill/outline opacity.
 * This lets you change the opacity of individual shader renders
 */
public class WorldDrawer {
    
    /**
     * Starts a new world rendering session
     * <br>
     * Must be called before any non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if already started
     */
    public static void start() {
        Instances.getWorldRender().start();
    }

    /**
     * Renders the current drawing session to the main vanilla framebuffer
     * <br>
     * Must be called after non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if start() was not called first
     *
     * @param matrices The matrix stack for rendering
     */
    public static void draw(PoseStack matrices) {
        Instances.getWorldRender().draw(matrices, Minecraft.getInstance().getMainRenderTarget());
    }

    /**
     * Renders the current drawing session to the specified framebuffer
     * <br>
     * Must be called after non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if start() was not called first
     *
     * @param matrices The matrix stack for rendering
     * @param framebuffer The framebuffer to render to
     */
    public static void draw(PoseStack matrices, RenderTarget framebuffer) {
        Instances.getWorldRender().draw(matrices, framebuffer);
    }

    /**
     * Renders a full box with both sides and outline
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void box(ClientColor color, float fillOpacity, float outlineOpacity, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().boxSides(color, fillOpacity, x1, y1, z1, x2, y2, z2, 0);
        Instances.getWorldRender().boxLines(color, outlineOpacity, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders a full box with both sides and outline
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void box(ClientColor color, float fillOpacity, float outlineOpacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().boxSides(color, fillOpacity, x1, y1, z1, x2, y2, z2, fade);
        Instances.getWorldRender().boxLines(color, outlineOpacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders a full box with both sides and outline
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines
     * @param box The box to render
     */
    public static void box(ClientColor color, float fillOpacity, float outlineOpacity, AABB box) {
        box(color, fillOpacity, outlineOpacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders a full box with both sides and outline
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines
     * @param box The box to render
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void box(ClientColor color, float fillOpacity, float outlineOpacity, AABB box, float fade) {
        box(color, fillOpacity, outlineOpacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, fade);
    }

    /**
     * Renders box outline lines
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void boxLines(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().boxLines(color, opacity, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders box outline lines
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxLines(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().boxLines(color, opacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box outline lines
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render lines for
     */
    public static void boxLines(ClientColor color, float opacity, AABB box) {
        Instances.getWorldRender().boxLines(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, 0);
    }

    /**
     * Renders box outline lines
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render lines for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxLines(ClientColor color, float opacity, AABB box, float fade) {
        Instances.getWorldRender().boxLines(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, fade);
    }

    /**
     * Renders box sides/faces
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void boxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().boxSides(color, opacity, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders box sides/faces
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().boxSides(color, opacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box sides/faces
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render sides for
     */
    public static void boxSides(ClientColor color, float opacity, AABB box) {
        Instances.getWorldRender().boxSides(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, 0);
    }

    /**
     * Renders box sides/faces
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxSides(ClientColor color, float opacity, AABB box, float fade) {
        Instances.getWorldRender().boxSides(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, fade);
    }

    /**
     * Renders a single box side/face
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void side(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().side(color, opacity, direction, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders a full box with both sides and outline using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void box(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (colorOption.singleOpacity) {
            box(colorOption.color, colorOption.fillOpacity, colorOption.fillOpacity, x1, y1, z1, x2, y2, z2);
        } else {
            box(colorOption.color, colorOption.fillOpacity, colorOption.outlineOpacity, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders a full box with both sides and outline using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void box(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        if (colorOption.singleOpacity) {
            box(colorOption.color, colorOption.fillOpacity, colorOption.fillOpacity, x1, y1, z1, x2, y2, z2, fade);
        } else {
            box(colorOption.color, colorOption.fillOpacity, colorOption.outlineOpacity, x1, y1, z1, x2, y2, z2, fade);
        }
    }

    /**
     * Renders a full box with both sides and outline using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render
     */
    public static void box(ColorOption.Value colorOption, AABB box) {
        if (colorOption.singleOpacity) {
            box(colorOption.color, colorOption.fillOpacity, colorOption.fillOpacity, box);
        } else {
            box(colorOption.color, colorOption.fillOpacity, colorOption.outlineOpacity, box);
        }
    }

    /**
     * Renders a full box with both sides and outline using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void box(ColorOption.Value colorOption, AABB box, float fade) {
        if (colorOption.singleOpacity) {
            box(colorOption.color, colorOption.fillOpacity, colorOption.fillOpacity, box, fade);
        } else {
            box(colorOption.color, colorOption.fillOpacity, colorOption.outlineOpacity, box, fade);
        }
    }

    /**
     * Renders box outline lines using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void boxLines(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        boxLines(colorOption.color, opacity, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box outline lines using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxLines(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        boxLines(colorOption.color, opacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box outline lines using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render lines for
     */
    public static void boxLines(ColorOption.Value colorOption, AABB box) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        boxLines(colorOption.color, opacity, box);
    }

    /**
     * Renders box outline lines using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render lines for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxLines(ColorOption.Value colorOption, AABB box, float fade) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        boxLines(colorOption.color, opacity, box, fade);
    }

    /**
     * Renders box sides/faces using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void boxSides(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2) {
        boxSides(colorOption.color, colorOption.fillOpacity, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxSides(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        boxSides(colorOption.color, colorOption.fillOpacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box sides/faces using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render sides for
     */
    public static void boxSides(ColorOption.Value colorOption, AABB box) {
        boxSides(colorOption.color, colorOption.fillOpacity, box);
    }

    /**
     * Renders box sides/faces using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void boxSides(ColorOption.Value colorOption, AABB box, float fade) {
        boxSides(colorOption.color, colorOption.fillOpacity, box, fade);
    }

    /**
     * Renders a single box side/face using ColorOption.Value
     * <br>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void side(ColorOption.Value colorOption, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        side(colorOption.color, colorOption.fillOpacity, direction, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void shaderBoxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderBoxSides(color, opacity, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders box sides/faces using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderBoxSides(ClientColor color, float opacity, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().shaderBoxSides(color, opacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box sides/faces using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param box The box to render sides for
     */
    public static void shaderBoxSides(ClientColor color, float opacity, AABB box) {
        Instances.getWorldRender().shaderBoxSides(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, 0);
    }

    /**
     * Renders box sides/faces using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderBoxSides(ClientColor color, float opacity, AABB box, float fade) {
        Instances.getWorldRender().shaderBoxSides(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, fade);
    }

    /**
     * Renders a single box side/face using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void shaderSide(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderSide(color, opacity, direction, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders a single box side/face using shader
     * <br>
     * Does not require start/draw calls
     *
     * @param color The color to use
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderSide(ClientColor color, float opacity, byte direction, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().shaderSide(color, opacity, direction, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void shaderBoxSides(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderBoxSides(colorOption.color, colorOption.outlineOpacity, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderBoxSides(ColorOption.Value colorOption, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().shaderBoxSides(colorOption.color, colorOption.outlineOpacity, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param box The box to render sides for
     */
    public static void shaderBoxSides(ColorOption.Value colorOption, AABB box) {
        Instances.getWorldRender().shaderBoxSides(colorOption.color, colorOption.outlineOpacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, 0);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderBoxSides(ColorOption.Value colorOption, AABB box, float fade) {
        Instances.getWorldRender().shaderBoxSides(colorOption.color, colorOption.outlineOpacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, fade);
    }

    /**
     * Renders a single box side/face using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void shaderSide(ColorOption.Value colorOption, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderSide(colorOption.color, colorOption.outlineOpacity, direction, x1, y1, z1, x2, y2, z2, 0);
    }

    /**
     * Renders a single box side/face using shader with ColorOption.Value
     * <br>
     * Does not require start/draw calls. Uses outline opacity for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void shaderSide(ColorOption.Value colorOption, byte direction, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        Instances.getWorldRender().shaderSide(colorOption.color, colorOption.outlineOpacity, direction, x1, y1, z1, x2, y2, z2, fade);
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (used as factor for both if useShader is true)
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicBox(ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderBoxSides(color, outlineOpacity, x1, y1, z1, x2, y2, z2, 0);
        } else {
            box(color, fillOpacity, outlineOpacity, x1, y1, z1, x2, y2, z2, 0);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (used as factor for both if useShader is true)
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBox(ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        if (useShader) {
            shaderBoxSides(color, outlineOpacity, x1, y1, z1, x2, y2, z2, fade);
        } else {
            box(color, fillOpacity, outlineOpacity, x1, y1, z1, x2, y2, z2, fade);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (used as factor for both if useShader is true)
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     */
    public static void dynamicBox(ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader, AABB box) {
        if (useShader) {
            shaderBoxSides(color, outlineOpacity, box, 0);
        } else {
            box(color, fillOpacity, outlineOpacity, box, 0);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (used as factor for both if useShader is true)
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBox(ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader, AABB box, float fade) {
        if (useShader) {
            shaderBoxSides(color, outlineOpacity, box, fade);
        } else {
            box(color, fillOpacity, outlineOpacity, box, fade);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicBoxSides(ClientColor color, float opacity, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderBoxSides(color, opacity, x1, y1, z1, x2, y2, z2, 0);
        } else {
            boxSides(color, opacity, x1, y1, z1, x2, y2, z2, 0);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBoxSides(ClientColor color, float opacity, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        if (useShader) {
            shaderBoxSides(color, opacity, x1, y1, z1, x2, y2, z2, fade);
        } else {
            boxSides(color, opacity, x1, y1, z1, x2, y2, z2, fade);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     */
    public static void dynamicBoxSides(ClientColor color, float opacity, boolean useShader, AABB box) {
        if (useShader) {
            shaderBoxSides(color, opacity, box, 0);
        } else {
            boxSides(color, opacity, box, 0);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBoxSides(ClientColor color, float opacity, boolean useShader, AABB box, float fade) {
        if (useShader) {
            shaderBoxSides(color, opacity, box, fade);
        } else {
            boxSides(color, opacity, box, fade);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicBox(ColorOption.Value colorOption, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2, 0);
        } else {
            box(colorOption, x1, y1, z1, x2, y2, z2, 0);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBox(ColorOption.Value colorOption, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        if (useShader) {
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2, fade);
        } else {
            box(colorOption, x1, y1, z1, x2, y2, z2, fade);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     */
    public static void dynamicBox(ColorOption.Value colorOption, boolean useShader, AABB box) {
        if (useShader) {
            shaderBoxSides(colorOption, box, 0);
        } else {
            box(colorOption, box, 0);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBox(ColorOption.Value colorOption, boolean useShader, AABB box, float fade) {
        if (useShader) {
            shaderBoxSides(colorOption, box, fade);
        } else {
            box(colorOption, box, fade);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicBoxSides(ColorOption.Value colorOption, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2, 0);
        } else {
            boxSides(colorOption, x1, y1, z1, x2, y2, z2, 0);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBoxSides(ColorOption.Value colorOption, boolean useShader, double x1, double y1, double z1, double x2, double y2, double z2, float fade) {
        if (useShader) {
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2, fade);
        } else {
            boxSides(colorOption, x1, y1, z1, x2, y2, z2, fade);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     */
    public static void dynamicBoxSides(ColorOption.Value colorOption, boolean useShader, AABB box) {
        if (useShader) {
            shaderBoxSides(colorOption, box, 0);
        } else {
            boxSides(colorOption, box, 0);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     * @param fade The fade value, 1F = full upwards fade, -1F = full downwards fade, 0F = no fade
     */
    public static void dynamicBoxSides(ColorOption.Value colorOption, boolean useShader, AABB box, float fade) {
        if (useShader) {
            shaderBoxSides(colorOption, box, fade);
        } else {
            boxSides(colorOption, box, fade);
        }
    }

    /**
     * Renders a single box side/face dynamically based on shader setting
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicSide(ClientColor color, float opacity, boolean useShader, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderSide(color, opacity, direction, x1, y1, z1, x2, y2, z2);
        } else {
            side(color, opacity, direction, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders a single box side/face dynamically based on shader setting using ColorOption.Value
     * <br>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param direction The direction of the side to render (use Dir constants)
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void dynamicSide(ColorOption.Value colorOption, boolean useShader, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        if (useShader) {
            shaderSide(colorOption, direction, x1, y1, z1, x2, y2, z2);
        } else {
            side(colorOption, direction, x1, y1, z1, x2, y2, z2);
        }
    }
}
