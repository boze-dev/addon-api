/**
 * Boze's GUI: the module GUI, the HUD editor and every popup, drawn by a theme.
 * <br>
 * To make a theme, implement {@link dev.boze.api.gui.theme.GuiTheme} and register it with
 * {@link dev.boze.api.gui.GuiThemes#register} from your addon's initialize. A theme creates a
 * {@link dev.boze.api.gui.view.GuiView} for the module GUI and one for the HUD editor, and a
 * {@link dev.boze.api.gui.kit.WidgetKit} that styles every popup it does not draw itself.
 * <br>
 * Views draw through a {@link dev.boze.api.gui.render.GuiPainter}, read modules and settings from
 * the {@link dev.boze.api.gui.model.GuiModel} and open popups ({@link dev.boze.api.gui.editor})
 * through the session's editor stack. Addons that only want a popup use
 * {@link dev.boze.api.gui.editor.GuiEditors} and {@link dev.boze.api.gui.Guis#openEditor}.
 */
package dev.boze.api.gui;
