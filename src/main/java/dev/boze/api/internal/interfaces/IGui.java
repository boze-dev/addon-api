package dev.boze.api.internal.interfaces;

import dev.boze.api.gui.editor.BindCaptureEditor;
import dev.boze.api.gui.editor.ChoiceEditor;
import dev.boze.api.gui.editor.ConfirmEditor;
import dev.boze.api.gui.editor.EditorButton;
import dev.boze.api.gui.editor.EditorSize;
import dev.boze.api.gui.editor.FormEditor;
import dev.boze.api.gui.editor.FormField;
import dev.boze.api.gui.editor.GuiEditor;
import dev.boze.api.gui.editor.ListEditor;
import dev.boze.api.gui.editor.ListEntry;
import dev.boze.api.gui.editor.NoticeEditor;
import dev.boze.api.gui.editor.NumberEditor;
import dev.boze.api.gui.editor.StaticColorEditor;
import dev.boze.api.gui.model.GuiModel;
import dev.boze.api.gui.render.GuiFont;
import dev.boze.api.gui.render.GuiImage;
import dev.boze.api.gui.render.GuiPaint;
import dev.boze.api.gui.theme.GuiTheme;
import dev.boze.api.gui.util.Spring;
import dev.boze.api.gui.util.TextFieldState;
import dev.boze.api.gui.view.GuiSession;
import dev.boze.api.render.ClientColor;
import dev.boze.api.utility.input.Bind;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public interface IGui {
    void registerTheme(GuiTheme theme);

    GuiTheme theme(String id);

    List<GuiTheme> themes();

    GuiTheme activeTheme();

    void selectTheme(String id);

    void openClickGui();

    void openHudEditor();

    void openEditor(GuiEditor editor);

    GuiSession session();

    GuiModel model();

    GuiPaint argbPaint(int argb);

    GuiPaint hsbPaint(float hue, float saturation, float brightness, float alpha);

    GuiPaint colorPaint(ClientColor color, float alpha);

    GuiPaint linearPaint(GuiPaint start, GuiPaint end, boolean horizontal);

    GuiImage itemImage(ItemStack stack);

    GuiImage spriteImage(Identifier sprite);

    GuiImage textureImage(Identifier texture);

    GuiImage svgImage(Identifier svg);

    Spring spring(DoubleSupplier settleSeconds);

    GuiFont guiFont();

    GuiFont vanillaFont();

    GuiFont loadFont(Identifier ttf);

    ConfirmEditor confirmEditor(String id, String title, List<String> lines, Runnable onConfirm);

    ChoiceEditor choiceEditor(String id, String title, List<EditorButton> options);

    NumberEditor numberEditor(String id, String label, double value, double min, double max, boolean integer, DoubleConsumer onSet);

    StaticColorEditor colorEditor(String id, String name, int rgb, IntConsumer onSave);

    BindCaptureEditor bindEditor(String id, String prompt, Consumer<Bind> onBind);

    NoticeEditor noticeEditor(String id, String title, List<String> lines, Runnable onDismiss);

    FormEditor formEditor(String id, String title, String prompt, List<FormField> fields, List<EditorButton> buttons, Runnable onSubmit,
                          EditorSize size);

    ListEditor listEditor(String id, String title, Supplier<List<ListEntry>> entries, TextFieldState input, String placeholder,
                          Consumer<String> onEnter, List<String> tabs, IntSupplier selectedTab, IntConsumer selectTab,
                          List<EditorButton> buttons, boolean compactButtons, EditorSize size);
}
