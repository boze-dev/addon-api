package dev.boze.api.gui.render;

/**
 * How the outline drawn around {@link GuiPainter#outlineMask()} looks. Start one with
 * {@link #builder}.
 */
public final class OutlineStyle {
    /**
     * How precisely wide outlines and glows are worked out. Lower is faster and slightly softer.
     */
    public enum Quality {
        HIGH,
        MEDIUM,
        LOW
    }

    private final GuiPaint color;
    private final double width;
    private final float glow;
    private final int bloomPasses;
    private final float bloomSpread;
    private final boolean bloomAdditive;
    private final Quality quality;
    private final float alpha;

    private OutlineStyle(Builder builder) {
        this.color = builder.color;
        this.width = builder.width;
        this.glow = builder.glow;
        this.bloomPasses = builder.bloomPasses;
        this.bloomSpread = builder.bloomSpread;
        this.bloomAdditive = builder.bloomAdditive;
        this.quality = builder.quality;
        this.alpha = builder.alpha;
    }

    /**
     * @param color Outline color; gradient client colors flow along the outline
     * @return A builder for a 1 pixel outline with no glow or bloom
     */
    public static Builder builder(GuiPaint color) {
        return new Builder(color);
    }

    public GuiPaint color() {
        return color;
    }

    /**
     * @return Width in GUI-scaled pixels
     */
    public double width() {
        return width;
    }

    public float glow() {
        return glow;
    }

    public int bloomPasses() {
        return bloomPasses;
    }

    public float bloomSpread() {
        return bloomSpread;
    }

    public boolean bloomAdditive() {
        return bloomAdditive;
    }

    public Quality quality() {
        return quality;
    }

    public float alpha() {
        return alpha;
    }

    public static final class Builder {
        private final GuiPaint color;
        private double width = 1;
        private float glow;
        private int bloomPasses;
        private float bloomSpread = 1;
        private boolean bloomAdditive;
        private Quality quality = Quality.HIGH;
        private float alpha = 1;

        private Builder(GuiPaint color) {
            this.color = color == null ? GuiPaint.argb(0xFFFFFFFF) : color;
        }

        /**
         * @param width Width in GUI-scaled pixels, drawn in whole screen pixels
         */
        public Builder width(double width) {
            this.width = Double.isFinite(width) ? Math.clamp(width, 0, 64) : 1;
            return this;
        }

        /**
         * @param glow How the outline fades toward its outer edge. 0 keeps it solid; above 0 it fades
         *             out, and smaller values fade sooner, for a softer glow. Boze's Gui module uses 0.01
         *             to 0.5.
         */
        public Builder glow(float glow) {
            this.glow = Float.isFinite(glow) ? Math.max(0F, glow) : 0F;
            return this;
        }

        /**
         * @param passes   Blur passes, 0 for no bloom
         * @param spread   How far the bloom reaches, 1 or more
         * @param additive Whether the bloom adds light instead of blending over
         */
        public Builder bloom(int passes, float spread, boolean additive) {
            this.bloomPasses = Math.clamp(passes, 0, 8);
            this.bloomSpread = Float.isFinite(spread) ? Math.max(1F, spread) : 1F;
            this.bloomAdditive = additive;
            return this;
        }

        public Builder quality(Quality quality) {
            this.quality = quality == null ? Quality.HIGH : quality;
            return this;
        }

        /**
         * @param alpha Overall opacity, 0 to 1
         */
        public Builder alpha(float alpha) {
            this.alpha = Float.isFinite(alpha) ? Math.clamp(alpha, 0F, 1F) : 1F;
            return this;
        }

        public OutlineStyle build() {
            return new OutlineStyle(this);
        }
    }
}
