package dev.boze.api.gui.editor;

import java.util.List;

/**
 * A message card shown on its own screen, such as the break reminder.
 */
public non-sealed interface NoticeEditor extends GuiEditor {
    /**
     * @return Message lines; the first is the main line
     */
    List<String> lines();

    void dismiss();
}
