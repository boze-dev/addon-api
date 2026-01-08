package dev.boze.api.option;

import com.google.gson.JsonObject;
import dev.boze.api.client.module.BaseModule;

/**
 * A page option that serves as a container for organizing options into pages
 * <br>
 * PageOption extends ParentOption and provides page-based navigation
 * <br>
 * When a page is selected, only options belonging to that page are shown
 */
public class PageOption extends ParentOption {

    private boolean selected = false;

    /**
     * Creates a new page option
     *
     * @param owner The module that owns this option
     * @param name The name of this option
     * @param description The description of this option
     */
    public PageOption(BaseModule owner, String name, String description) {
        super(owner, name, description);
    }

    /**
     * Gets whether this page is currently selected
     *
     * @return true if selected, false otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets whether this page is currently selected
     *
     * @param selected true to select, false to deselect
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = super.toJson();
        object.addProperty("selected", selected);
        return object;
    }

    @Override
    public Boolean fromJson(JsonObject object) {
        super.fromJson(object);
        if (object.has("selected")) {
            selected = object.get("selected").getAsBoolean();
        }
        return false;
    }
}
