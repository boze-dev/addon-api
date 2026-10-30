package dev.boze.api.gui.editor;

import dev.boze.api.gui.render.GuiImage;
import dev.boze.api.gui.render.GuiPaint;

import java.util.ArrayList;
import java.util.List;

/**
 * A row in a list editor.
 *
 * @param key         Stable key of the row
 * @param label       Row text
 * @param selected    Whether the row is the current choice
 * @param image       Picture before the label, or null
 * @param swatch      Color square before the label, or null
 * @param leading     Icon button before the label, or null
 * @param detail      Text before the actions, or null
 * @param detailPaint Color of {@code detail}, or null for the theme's text color
 * @param actions     Icon buttons at the end of the row, first one rightmost
 * @param onClick     What clicking the row outside its buttons does, or null
 */
public record ListEntry(String key, String label, boolean selected, GuiImage image, GuiPaint swatch, RowAction leading,
                        String detail, GuiPaint detailPaint, List<RowAction> actions, Runnable onClick) {
    public static Builder builder(String key, String label) {
        return new Builder(key, label);
    }

    public void click() {
        if (onClick != null) onClick.run();
    }

    public static final class Builder {
        private final String key;
        private final String label;
        private boolean selected;
        private GuiImage image;
        private GuiPaint swatch;
        private RowAction leading;
        private String detail;
        private GuiPaint detailPaint;
        private final List<RowAction> actions = new ArrayList<>();
        private Runnable onClick;

        private Builder(String key, String label) {
            this.key = key;
            this.label = label;
        }

        public Builder selected(boolean selected) {
            this.selected = selected;
            return this;
        }

        public Builder image(GuiImage image) {
            this.image = image;
            return this;
        }

        public Builder swatch(GuiPaint swatch) {
            this.swatch = swatch;
            return this;
        }

        public Builder leading(RowAction leading) {
            this.leading = leading;
            return this;
        }

        public Builder detail(String detail, GuiPaint paint) {
            this.detail = detail;
            this.detailPaint = paint;
            return this;
        }

        /**
         * Adds an action left of the ones added before.
         */
        public Builder action(RowAction action) {
            actions.add(action);
            return this;
        }

        public Builder onClick(Runnable onClick) {
            this.onClick = onClick;
            return this;
        }

        public ListEntry build() {
            return new ListEntry(key, label, selected, image, swatch, leading, detail, detailPaint, List.copyOf(actions), onClick);
        }
    }
}
