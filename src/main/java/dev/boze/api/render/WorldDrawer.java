package dev.boze.api.render;

import dev.boze.api.internal.Instances;
import dev.boze.api.option.ColorOption;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;

/**
 * World rendering API for drawing 3D boxes in the world
 * <p></p>
 * Provides methods for rendering boxes in 3D world space
 * <p></p>
 * All drawing operations must be wrapped between {@link #start()} and {@link #draw(MatrixStack, Framebuffer)} calls
 * <p></p>
 * You can, of course, draw more than one box per start/draw call
 * <p></p>
 * See {@link dev.boze.api.event.EventShader} before using shader renders
 */
public class WorldDrawer {
    
    /**
     * Starts a new world rendering session
     * <p></p>
     * Must be called before any non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if already started
     */
    public static void start() {
        Instances.getWorldRender().start();
    }

    /**
     * Renders the current drawing session to the main vanilla framebuffer
     * <p></p>
     * Must be called after non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if start() was not called first
     *
     * @param matrices The matrix stack for rendering
     */
    public static void draw(MatrixStack matrices) {
        Instances.getWorldRender().draw(matrices, MinecraftClient.getInstance().getFramebuffer());
    }

    /**
     * Renders the current drawing session to the specified framebuffer
     * <p></p>
     * Must be called after non-shader drawing operations.
     * Shader methods (shaderBoxSides, shaderSide, and dynamic methods when useShader=true) do not require start/draw.
     * Throws RuntimeException if start() was not called first
     *
     * @param matrices The matrix stack for rendering
     * @param framebuffer The framebuffer to render to
     */
    public static void draw(MatrixStack matrices, Framebuffer framebuffer) {
        Instances.getWorldRender().draw(matrices, framebuffer);
    }

    /**
     * Renders a full box with both sides and outline
     * <p></p>
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
        Instances.getWorldRender().boxSides(color, fillOpacity, x1, y1, z1, x2, y2, z2);
        Instances.getWorldRender().boxLines(color, outlineOpacity, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders a full box with both sides and outline
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines
     * @param box The box to render
     */
    public static void box(ClientColor color, float fillOpacity, float outlineOpacity, Box box) {
        box(color, fillOpacity, outlineOpacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders box outline lines
     * <p></p>
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
        Instances.getWorldRender().boxLines(color, opacity, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box outline lines
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render lines for
     */
    public static void boxLines(ClientColor color, float opacity, Box box) {
        Instances.getWorldRender().boxLines(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders box sides/faces
     * <p></p>
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
        Instances.getWorldRender().boxSides(color, opacity, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param box The box to render sides for
     */
    public static void boxSides(ClientColor color, float opacity, Box box) {
        Instances.getWorldRender().boxSides(color, opacity, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders a single box side/face
     * <p></p>
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
     * <p></p>
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
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render
     */
    public static void box(ColorOption.Value colorOption, Box box) {
        if (colorOption.singleOpacity) {
            box(colorOption.color, colorOption.fillOpacity, colorOption.fillOpacity, box);
        } else {
            box(colorOption.color, colorOption.fillOpacity, colorOption.outlineOpacity, box);
        }
    }

    /**
     * Renders box outline lines using ColorOption.Value
     * <p></p>
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
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render lines for
     */
    public static void boxLines(ColorOption.Value colorOption, Box box) {
        float opacity = colorOption.singleOpacity ? colorOption.fillOpacity : colorOption.outlineOpacity;
        boxLines(colorOption.color, opacity, box);
    }

    /**
     * Renders box sides/faces using ColorOption.Value
     * <p></p>
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
     * <p></p>
     * Throws RuntimeException if start() was not called first
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param box The box to render sides for
     */
    public static void boxSides(ColorOption.Value colorOption, Box box) {
        boxSides(colorOption.color, colorOption.fillOpacity, box);
    }

    /**
     * Renders a single box side/face using ColorOption.Value
     * <p></p>
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
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
     *
     * @param color The color to use
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param z1 Minimum Z coordinate
     * @param x2 Maximum X coordinate
     * @param y2 Maximum Y coordinate
     * @param z2 Maximum Z coordinate
     */
    public static void shaderBoxSides(ClientColor color, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderBoxSides(color, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces using shader
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
     *
     * @param color The color to use
     * @param box The box to render sides for
     */
    public static void shaderBoxSides(ClientColor color, Box box) {
        Instances.getWorldRender().shaderBoxSides(color, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders a single box side/face using shader
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
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
    public static void shaderSide(ClientColor color, byte direction, double x1, double y1, double z1, double x2, double y2, double z2) {
        Instances.getWorldRender().shaderSide(color, direction, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
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
        Instances.getWorldRender().shaderBoxSides(colorOption.color, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders box sides/faces using shader with ColorOption.Value
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
     *
     * @param colorOption The color option value containing color settings
     * @param box The box to render sides for
     */
    public static void shaderBoxSides(ColorOption.Value colorOption, Box box) {
        Instances.getWorldRender().shaderBoxSides(colorOption.color, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }

    /**
     * Renders a single box side/face using shader with ColorOption.Value
     * <p></p>
     * Does not require start/draw calls. Uses opacity 1F for shader rendering
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
        Instances.getWorldRender().shaderSide(colorOption.color, direction, x1, y1, z1, x2, y2, z2);
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <p></p>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (ignored if useShader is true)
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
            shaderBoxSides(color, x1, y1, z1, x2, y2, z2);
        } else {
            box(color, fillOpacity, outlineOpacity, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting
     * <p></p>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param fillOpacity The opacity for filled sides
     * @param outlineOpacity The opacity for outline lines (ignored if useShader is true)
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     */
    public static void dynamicBox(ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader, Box box) {
        if (useShader) {
            shaderBoxSides(color, box);
        } else {
            box(color, fillOpacity, outlineOpacity, box);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <p></p>
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
            shaderBoxSides(color, x1, y1, z1, x2, y2, z2);
        } else {
            boxSides(color, opacity, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting
     * <p></p>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param color The color to use
     * @param opacity The opacity value
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     */
    public static void dynamicBoxSides(ClientColor color, float opacity, boolean useShader, Box box) {
        if (useShader) {
            shaderBoxSides(color, box);
        } else {
            boxSides(color, opacity, box);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <p></p>
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
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2);
        } else {
            box(colorOption, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders a full box dynamically based on shader setting using ColorOption.Value
     * <p></p>
     * If useShader is true, renders only sides using shader (no lines).
     * If useShader is false, renders full box with sides and lines.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render
     */
    public static void dynamicBox(ColorOption.Value colorOption, boolean useShader, Box box) {
        if (useShader) {
            shaderBoxSides(colorOption, box);
        } else {
            box(colorOption, box);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <p></p>
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
            shaderBoxSides(colorOption, x1, y1, z1, x2, y2, z2);
        } else {
            boxSides(colorOption, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders box sides dynamically based on shader setting using ColorOption.Value
     * <p></p>
     * If useShader is true, uses shader rendering.
     * If useShader is false, uses regular rendering.
     * Throws RuntimeException if start() was not called first and useShader is false
     *
     * @param colorOption The color option value containing color and opacity settings
     * @param useShader Whether to use shader rendering
     * @param box The box to render sides for
     */
    public static void dynamicBoxSides(ColorOption.Value colorOption, boolean useShader, Box box) {
        if (useShader) {
            shaderBoxSides(colorOption, box);
        } else {
            boxSides(colorOption, box);
        }
    }

    /**
     * Renders a single box side/face dynamically based on shader setting
     * <p></p>
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
            shaderSide(color, direction, x1, y1, z1, x2, y2, z2);
        } else {
            side(color, opacity, direction, x1, y1, z1, x2, y2, z2);
        }
    }

    /**
     * Renders a single box side/face dynamically based on shader setting using ColorOption.Value
     * <p></p>
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
