package dev.boze.api.client.module;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.boze.api.client.ModuleManager;
import dev.boze.api.utility.input.Bind;
import dev.boze.api.option.Option;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

/**
 * Base interface for all modules
 * <br>
 * A module is a self-contained piece of functionality that can be enabled or disabled
 * <br>
 * Each module has a name, description, title (which can be changed), and a keybind
 * <br>
 * Modules can have settings which are automatically serialized and deserialized
 */
public interface BaseModule {

    /**
     * Gets the internal name of this module
     *
     * @return The module's name
     */
    String getName();

    /**
     * Gets the description of this module
     *
     * @return The module's description
     */
    String getDescription();

    /**
     * Modify the description of the module
     * <br>
     * This resets when you re-launch
     * <br>
     * Supports newline escape sequence
     *
     * @param description Description to set
     */
    void setDescription(String description);

    /**
     * Gets the display title of this module
     *
     * @return The module's title
     */
    String getTitle();

    /**
     * Sets the display title of this module
     *
     * @param newTitle The new title
     */
    void setTitle(String newTitle);

    /**
     * Gets whether this module is enabled
     *
     * @return true if the module is enabled, false otherwise
     */
    boolean getState();

    /**
     * Sets whether this module is enabled
     * <br>
     * This will call onEnable() or onDisable() as appropriate
     *
     * @param newState The new state
     * @return true if the state changed, false if it was already in that state
     */
    boolean setState(boolean newState);

    /**
     * Called when this module is enabled
     * <br>
     * For many client modules, sets initial state
     * <br>
     * Call instead of setState to not affect the state
     */
    void onEnable();

    /**
     * Called when this module is disabled
     * <br>
     * For many client modules, clears data
     * <br>
     * In some, restores changes, i.e. Timer
     * <br>
     * Call instead of setState to not affect the state
     */
    void onDisable();

    /**
     * Gets the current bind
     *
     * @return The module's bind
     */
    Bind getBind();

    /**
     * Sets the bind
     *
     * @param newBind The new bind
     */
    void setBind(Bind newBind);

    /**
     * Method called by ArrayList to get info
     *
     * @return Info to show in ArrayList brackets
     */
    String getArrayListInfo();

    /**
     * Gets whether this module is visible in the ArrayList
     *
     * @return true if the module is visible in ArrayList, false otherwise
     */
    boolean isVisible();

    /**
     * Sets whether this module is visible in the ArrayList
     *
     * @param visible true to make the module visible in ArrayList, false to hide it
     */
    void setVisible(boolean visible);

    /**
     * Gets whether notifications should be shown when this module is toggled
     *
     * @return true if notifications should be shown, false otherwise
     */
    boolean shouldNotify();

    /**
     * Sets whether notifications should be shown when this module is toggled
     *
     * @param notify true to show notifications, false to hide them
     */
    void setNotify(boolean notify);

    /**
     * Gets whether this module should only be active while the bind key is held
     *
     * @return true if the module only activates while holding the key, false for toggle behavior
     */
    boolean isOnlyWhileHolding();

    /**
     * Sets whether this module should only be active while the bind key is held
     *
     * @param onlyWhileHolding true for hold-to-activate, false for toggle behavior
     */
    void setOnlyWhileHolding(boolean onlyWhileHolding);

    /**
     * Gets a list of this module's options
     *
     * @return the list of this module's options
     */
    List<Option<?>> getOptions();

    /**
     * Command argument for base modules
     */
    class BaseModuleArgument implements ArgumentType<BaseModule> {

        private static final Collection<String> EXAMPLES = ModuleManager.getModules()
                .stream()
                .limit(3)
                .map(BaseModule::getTitle)
                .collect(Collectors.toList());

        private static final DynamicCommandExceptionType NO_SUCH_MODULE = new DynamicCommandExceptionType(o -> Component.literal("Module with name " + o + " doesn't exist."));

        /**
         * Creates a new BaseModuleArgument for parsing module names
         *
         * @return A new BaseModuleArgument instance
         */
        public static BaseModuleArgument module() {
            return new BaseModuleArgument();
        }

        /**
         * Gets a BaseModule from a command context
         *
         * @param context The command context
         * @param name The name of the argument
         * @return The parsed BaseModule
         */
        public static BaseModule getModule(final CommandContext<?> context, final String name) {
            return context.getArgument(name, BaseModule.class);
        }

        @Override
        public BaseModule parse(StringReader reader) throws CommandSyntaxException {
            String argument = reader.readString();
            BaseModule module = null;

            for (BaseModule m : ModuleManager.getModules()) {
                if (m.getTitle().equalsIgnoreCase(argument) || m.getName().equalsIgnoreCase(argument)) {
                    module = m;
                    break;
                }
            }

            if (module == null) throw NO_SUCH_MODULE.create(argument);

            return module;
        }

        @Override
        public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
            return SharedSuggestionProvider.suggest(
                ModuleManager.getModules().stream()
                    .map(BaseModule::getTitle)
                    .collect(Collectors.toList()),
                builder
            );
        }

        @Override
        public Collection<String> getExamples() {
            return EXAMPLES;
        }
    }
}
