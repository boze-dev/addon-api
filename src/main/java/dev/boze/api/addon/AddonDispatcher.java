package dev.boze.api.addon;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.server.permissions.PermissionSet;

/**
 * Container class for addon command dispatching
 * <br>
 * Each addon has its own dispatcher that manages its commands
 * <br>
 * The dispatcher handles command registration, execution, and provides access to all registered commands
 * <br>
 * All addons must use this to register and handle commands
 */
public class AddonDispatcher {

    /**
     * The underlying command dispatcher
     */
    public static final CommandDispatcher<SharedSuggestionProvider> dispatcher = new CommandDispatcher<>();

    /**
     * List of registered commands
     */
    private final List<AddonCommand> commands = new ArrayList<>();

    /**
     * Gets the underlying command dispatcher
     * <br>
     * This can be used for advanced command handling
     *
     * @return The command dispatcher
     */
    public CommandDispatcher<SharedSuggestionProvider> getDispatcher() {
        return dispatcher;
    }

    /**
     * Dispatches a command using the default client command source
     * <br>
     * This is the most common way to execute commands
     *
     * @param command The command to dispatch
     * @throws CommandSyntaxException If the command has a syntax error
     */
    public void dispatch(String command) throws CommandSyntaxException {
        dispatch(command, new ClientSuggestionProvider(null, Minecraft.getInstance(), PermissionSet.ALL_PERMISSIONS));
    }

    /**
     * Dispatches a command with a specific command source
     * <br>
     * This allows for custom command execution contexts
     *
     * @param command The command to dispatch
     * @param source The command source to use
     * @throws CommandSyntaxException If the command has a syntax error
     */
    public void dispatch(String command, SharedSuggestionProvider source) throws CommandSyntaxException {
        getDispatcher().execute(command, source);
    }

    /**
     * Registers a single command
     * <br>
     * The command will be added to the list of commands and registered with the dispatcher
     *
     * @param command The command to register
     */
    public void registerCommand(AddonCommand command) {
        commands.add(command);
        command.register(dispatcher);
    }

    /**
     * Registers multiple commands at once
     * <br>
     * Each command will be added to the list of commands and registered with the dispatcher
     *
     * @param commands The commands to register
     */
    public void registerCommands(AddonCommand... commands) {
        for (AddonCommand command : commands) {
            registerCommand(command);
        }
    }

    /**
     * Gets all registered commands
     * <br>
     * This can be used to get information about available commands
     *
     * @return List of all registered commands
     */
    public List<AddonCommand> getCommands() {
        return commands;
    }
} 