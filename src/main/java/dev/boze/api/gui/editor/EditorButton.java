package dev.boze.api.gui.editor;

import java.util.function.BooleanSupplier;

/**
 * A button in an editor.
 *
 * @param role    What kind of action it is; themes may style roles differently
 * @param enabled Whether it can be pressed right now
 */
public record EditorButton(String label, Role role, BooleanSupplier enabled, Runnable action) {
    public enum Role {
        /**
         * The main action, such as Save, Add or Login.
         */
        PRIMARY,

        /**
         * Any other action, such as Cancel or Close.
         */
        SECONDARY,

        /**
         * An action that removes or overwrites something.
         */
        DESTRUCTIVE
    }

    public EditorButton {
        if (role == null) role = Role.SECONDARY;
        if (enabled == null) enabled = () -> true;
    }

    /**
     * A secondary button that is always enabled.
     */
    public EditorButton(String label, Runnable action) {
        this(label, Role.SECONDARY, null, action);
    }

    /**
     * A button that is always enabled.
     */
    public EditorButton(String label, Role role, Runnable action) {
        this(label, role, null, action);
    }

    public boolean isEnabled() {
        return enabled.getAsBoolean();
    }

    /**
     * Runs the action if the button is enabled.
     */
    public void press() {
        if (isEnabled()) action.run();
    }
}
