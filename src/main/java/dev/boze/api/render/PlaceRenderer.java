package dev.boze.api.render;

import dev.boze.api.BozeInstance;
import dev.boze.api.event.EventShader;
import dev.boze.api.event.EventWorldRender;
import dev.boze.api.internal.Instances;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import java.util.HashSet;
import java.util.Set;

/**
 * PlaceRenderer handles rendering of block placement visualizations in the world.
 * <br>
 * Manages a collection of placement records and renders them with optional animations.
 * Automatically handles shader preparation and world rendering events.
 */
public class PlaceRenderer {

    /**
     * Record containing placement visualization data
     */
    public record PlacementRecord(
            BlockPos pos,
            long placeTime,
            long duration,
            ClientColor color,
            float fillOpacity,
            float outlineOpacity,
            float animOpacity,
            float animGrow,
            float animShrink,
            boolean useShader
    ) {

        /**
         * Creates a placement record with full animation and shader settings.
         *
         * @param pos The block position to render
         * @param placeTime The timestamp when placement occurred (System.currentTimeMillis())
         * @param duration How long to render the placement in milliseconds
         * @param color The color to use for rendering
         * @param fillOpacity Opacity for filled box faces (0.0 to 1.0)
         * @param outlineOpacity Opacity for box outline edges (0.0 to 1.0)
         * @param animOpacity Opacity animation duration (0 = no fade, 1 = fade over entire time)
         * @param animGrow Grow animation duration (starts at beginning)
         * @param animShrink Shrink animation duration (starts near end)
         * @param useShader Whether to use shader rendering instead of regular rendering
         */
        public PlacementRecord {
        }

        /**
         * Creates a placement record without animation or shader settings.
         * Animation parameters default to 0 (no animation), useShader defaults to false.
         *
         * @param pos The block position to render
         * @param placeTime The timestamp when placement occurred (System.currentTimeMillis())
         * @param duration How long to render the placement in milliseconds
         * @param color The color to use for rendering
         * @param fillOpacity Opacity for filled box faces (0.0 to 1.0)
         * @param outlineOpacity Opacity for box outline edges (0.0 to 1.0)
         */
        public PlacementRecord(BlockPos pos, long placeTime, long duration,
                              ClientColor color, float fillOpacity, float outlineOpacity) {
            this(pos, placeTime, duration, color, fillOpacity, outlineOpacity, 0f, 0f, 0f, false);
        }

        /**
         * Creates a placement record without animation settings but with shader setting.
         * Animation parameters default to 0 (no animation).
         *
         * @param pos The block position to render
         * @param placeTime The timestamp when placement occurred (System.currentTimeMillis())
         * @param duration How long to render the placement in milliseconds
         * @param color The color to use for rendering
         * @param fillOpacity Opacity for filled box faces (0.0 to 1.0)
         * @param outlineOpacity Opacity for box outline edges (0.0 to 1.0)
         * @param useShader Whether to use shader rendering instead of regular rendering
         */
        public PlacementRecord(BlockPos pos, long placeTime, long duration,
                              ClientColor color, float fillOpacity, float outlineOpacity, boolean useShader) {
            this(pos, placeTime, duration, color, fillOpacity, outlineOpacity, 0f, 0f, 0f, useShader);
        }

        /**
         * Creates a placement record with animation settings but without shader setting.
         * useShader defaults to false.
         *
         * @param pos The block position to render
         * @param placeTime The timestamp when placement occurred (System.currentTimeMillis())
         * @param duration How long to render the placement in milliseconds
         * @param color The color to use for rendering
         * @param fillOpacity Opacity for filled box faces (0.0 to 1.0)
         * @param outlineOpacity Opacity for box outline edges (0.0 to 1.0)
         * @param animOpacity Opacity animation duration (0 = no fade, 1 = fade over entire time)
         * @param animGrow Grow animation duration (starts at beginning)
         * @param animShrink Shrink animation duration (starts near end)
         */
        public PlacementRecord(BlockPos pos, long placeTime, long duration,
                              ClientColor color, float fillOpacity, float outlineOpacity,
                              float animOpacity, float animGrow, float animShrink) {
            this(pos, placeTime, duration, color, fillOpacity, outlineOpacity, animOpacity, animGrow, animShrink, false);
        }
    }

    private static final Set<PlacementRecord> placements = new HashSet<>();

    static {
        // Register package and subscribe to events
        BozeInstance.INSTANCE.registerPackage("dev.boze.api.render");
        BozeInstance.INSTANCE.subscribe(PlaceRenderer.class);
    }

    /**
     * Adds a placement record for rendering
     *
     * @param record The placement record to add
     */
    public static void addPlacement(PlacementRecord record) {
        placements.add(record);
    }

    /**
     * Removes a placement record
     *
     * @param record The placement record to remove
     */
    public static void removePlacement(PlacementRecord record) {
        placements.remove(record);
    }

    /**
     * Clears all placement records
     */
    public static void clearPlacements() {
        placements.clear();
    }

    /**
     * Gets the current set of placement records
     *
     * @return The set of placement records
     */
    public static Set<PlacementRecord> getPlacements() {
        return placements;
    }

    /**
     * Gets the correct render position for a block placement based on the hit result.
     * <br>
     * This method determines whether the placement was an air placement or a normal placement
     * and returns the appropriate position for rendering the placement visualization.
     *
     * @param result The block hit result from the placement operation
     * @return The position where the block was actually placed
     */
    public static BlockPos getRenderPos(BlockHitResult result) {
        boolean isAir = Instances.getWorld().getBlockState(result.getBlockPos()).isAir();
        if (isAir) {
            return result.getBlockPos();
        } else {
            return result.getBlockPos().relative(result.getDirection());
        }
    }

    @EventHandler
    private static void onShader(EventShader event) {
        // Prepare colors for shader rendering
        for (PlacementRecord record : placements) {
            if (record.color != null && record.useShader) {
                event.prepare(record.color);
            }
        }
    }

    @EventHandler
    private static void onWorldRender(EventWorldRender event) {
        long currentTime = System.currentTimeMillis();

        // Clean up expired placements
        placements.removeIf(record -> currentTime - record.placeTime > record.duration);

        // Start world rendering session
        WorldDrawer.start();

        // Render active placements
        for (PlacementRecord record : placements) {
            renderPlacement(event, record, currentTime);
        }

        // Finish world rendering session
        WorldDrawer.draw(event.matrices);
    }

    private static void renderPlacement(EventWorldRender event, PlacementRecord record, long currentTime) {
        long endTime = record.placeTime + record.duration;

        if (currentTime > endTime) return;

        // Calculate progress from 0.0 to 1.0 over the render duration
        double progress = Math.max(0.0, Math.min(1.0, (double) (currentTime - record.placeTime) / record.duration));

        // Calculate box bounds
        double x1 = record.pos.getX();
        double y1 = record.pos.getY();
        double z1 = record.pos.getZ();
        double x2 = x1 + 1;
        double y2 = y1 + 1;
        double z2 = z1 + 1;

        AABB box = new AABB(x1, y1, z1, x2, y2, z2);

        // Apply grow animation
        if (record.animGrow > 0) {
            double growProgress = Math.max(0.0, Math.min(1.0, progress / record.animGrow));
            box = box.inflate((1.0 - growProgress) * -0.5);
        }

        // Apply shrink animation
        if (record.animShrink > 0) {
            double shrinkProgress = Math.max(0.0, Math.min(1.0, (progress - (1.0 - record.animShrink)) / record.animShrink));
            if (shrinkProgress > 0) {
                box = box.inflate(shrinkProgress * -0.5);
            }
        }

        // Apply opacity animation
        float fillOpacity = record.fillOpacity;
        float outlineOpacity = record.outlineOpacity;
        if (record.animOpacity > 0) {
            double opacityProgress = Math.max(0.0, Math.min(1.0, (progress - (1.0 - record.animOpacity)) / record.animOpacity));
            float opacityFactor = (float) (1.0 - opacityProgress);
            fillOpacity *= opacityFactor;
            outlineOpacity *= opacityFactor;
        }

        // Render the box
        WorldDrawer.dynamicBox(record.color, fillOpacity, outlineOpacity, record.useShader, box);
    }
}
