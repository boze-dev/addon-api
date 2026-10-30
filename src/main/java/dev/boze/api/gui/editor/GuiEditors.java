package dev.boze.api.gui.editor;

import dev.boze.api.gui.util.TextFieldState;
import dev.boze.api.internal.Instances;
import dev.boze.api.utility.input.Bind;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/**
 * Built-in editors (popups) for addons, drawn by the active theme like Boze's own. Open them with
 * {@link dev.boze.api.gui.Guis#openEditor}.
 * <br>
 * Ids name what is edited, for example {@code myaddon:waypoints}; themes can use them to give an
 * editor a view of its own. Callbacks run on the render thread.
 */
public final class GuiEditors {
    private GuiEditors() {
    }

    /**
     * A message with Cancel and Confirm. Either button closes it; Confirm runs {@code onConfirm}
     * first.
     */
    public static ConfirmEditor confirm(String id, String title, List<String> lines, Runnable onConfirm) {
        return Instances.getGui().confirmEditor(id, title, List.copyOf(lines), onConfirm);
    }

    /**
     * A column of buttons. Pressing one closes the editor and then runs it.
     */
    public static ChoiceEditor choice(String id, String title, List<EditorButton> options) {
        return Instances.getGui().choiceEditor(id, title, List.copyOf(options));
    }

    /**
     * Typed entry for one number, with Cancel, Reset and Set. Set passes the typed value, clamped to
     * {@code min..max}; Reset passes {@code value}.
     *
     * @param label   What the number is, shown as the title
     * @param integer Whether only whole numbers can be typed
     */
    public static NumberEditor number(String id, String label, double value, double min, double max, boolean integer,
                                      DoubleConsumer onSet) {
        return Instances.getGui().numberEditor(id, label, value, Math.min(min, max), Math.max(min, max), integer, onSet);
    }

    /**
     * A color picker. Save closes it and passes the color as RGB, without alpha.
     *
     * @param name What the color is, shown in the title
     * @param rgb  Starting color
     */
    public static StaticColorEditor color(String id, String name, int rgb, IntConsumer onSave) {
        return Instances.getGui().colorEditor(id, name, rgb & 0xFFFFFF, onSave);
    }

    /**
     * Waits for the next key or mouse button, then closes. Escape gives a cleared bind
     * ({@code new Bind(false, -1)}). Always shown on a screen of its own.
     */
    public static BindCaptureEditor bind(String id, String prompt, Consumer<Bind> onBind) {
        return Instances.getGui().bindEditor(id, prompt, onBind);
    }

    /**
     * A message card. Its button and Escape both close it and run {@code onDismiss}. Always shown
     * on a screen of its own.
     *
     * @param lines Message lines; the first is the main line
     */
    public static NoticeEditor notice(String id, String title, List<String> lines, Runnable onDismiss) {
        return Instances.getGui().noticeEditor(id, title, List.copyOf(lines), onDismiss);
    }

    /**
     * Text fields and buttons.
     *
     * @param prompt Line shown above the fields
     */
    public static FormBuilder form(String id, String title, String prompt) {
        return new FormBuilder(id, title, prompt);
    }

    /**
     * A list of rows with an optional text field and buttons below it.
     *
     * @param entries Current rows, read every frame
     */
    public static ListBuilder list(String id, String title, Supplier<List<ListEntry>> entries) {
        return new ListBuilder(id, title, entries);
    }

    public static final class FormBuilder {
        private final String id, title, prompt;
        private final List<FormField> fields = new ArrayList<>();
        private final List<EditorButton> buttons = new ArrayList<>();
        private Consumer<FormEditor> onSubmit;
        private EditorSize size;
        private FormEditor built;

        private FormBuilder(String id, String title, String prompt) {
            this.id = id;
            this.title = title;
            this.prompt = prompt;
        }

        public FormBuilder field(FormField field) {
            fields.add(field);
            return this;
        }

        /**
         * Adds a button. Its action gets the form, to read fields and close it.
         */
        public FormBuilder button(String label, EditorButton.Role role, Consumer<FormEditor> action) {
            return button(label, role, null, action);
        }

        public FormBuilder button(String label, EditorButton.Role role, BooleanSupplier enabled, Consumer<FormEditor> action) {
            buttons.add(new EditorButton(label, role, enabled, () -> action.accept(built)));
            return this;
        }

        /**
         * What Enter does, for example pressing the main button. By default Enter does nothing.
         */
        public FormBuilder onSubmit(Consumer<FormEditor> submit) {
            this.onSubmit = submit;
            return this;
        }

        public FormBuilder size(EditorSize size) {
            this.size = size;
            return this;
        }

        public FormEditor build() {
            Consumer<FormEditor> submit = onSubmit;
            built = Instances.getGui().formEditor(id, title, prompt, List.copyOf(fields), List.copyOf(buttons),
                    submit == null ? null : () -> submit.accept(built), size);
            return built;
        }
    }

    public static final class ListBuilder {
        private final String id, title;
        private final Supplier<List<ListEntry>> entries;
        private final List<EditorButton> buttons = new ArrayList<>();
        private TextFieldState input;
        private String placeholder = "";
        private Consumer<String> onEnter;
        private List<String> tabs = List.of();
        private IntSupplier selectedTab = () -> 0;
        private IntConsumer selectTab = tab -> {
        };
        private boolean compact;
        private EditorSize size = new EditorSize(128, 288);
        private ListEditor built;

        private ListBuilder(String id, String title, Supplier<List<ListEntry>> entries) {
            this.id = id;
            this.title = title;
            this.entries = entries;
        }

        /**
         * A text field left of the buttons. Enter passes what was typed, if anything, and clears it.
         */
        public ListBuilder input(TextFieldState field, String placeholder, Consumer<String> onEnter) {
            this.input = field;
            this.placeholder = placeholder == null ? "" : placeholder;
            this.onEnter = onEnter;
            return this;
        }

        /**
         * Tabs above the list. Read the selected tab in {@code entries} to show its rows.
         */
        public ListBuilder tabs(List<String> names, IntSupplier selected, IntConsumer select) {
            this.tabs = List.copyOf(names);
            this.selectedTab = selected;
            this.selectTab = select;
            return this;
        }

        /**
         * Adds a button. Its action gets the list, for example to close it.
         */
        public ListBuilder button(String label, EditorButton.Role role, Consumer<ListEditor> action) {
            return button(label, role, null, action);
        }

        public ListBuilder button(String label, EditorButton.Role role, BooleanSupplier enabled, Consumer<ListEditor> action) {
            buttons.add(new EditorButton(label, role, enabled, () -> action.accept(built)));
            return this;
        }

        /**
         * Sets the buttons together in the middle instead of spread across the row.
         */
        public ListBuilder compactButtons() {
            this.compact = true;
            return this;
        }

        /**
         * @param size Preferred size; 128 by 288 units by default
         */
        public ListBuilder size(EditorSize size) {
            if (size != null) this.size = size;
            return this;
        }

        public ListEditor build() {
            built = Instances.getGui().listEditor(id, title, entries, input, placeholder, onEnter, tabs, selectedTab, selectTab,
                    List.copyOf(buttons), compact, size);
            return built;
        }
    }
}
