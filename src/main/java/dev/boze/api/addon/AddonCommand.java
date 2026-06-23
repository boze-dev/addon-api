package dev.boze.api.addon;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.commands.SharedSuggestionProvider;

/**
 * Base class for addon commands.
 * All addon commands should extend this class.
 */
public abstract class AddonCommand {

    private final String name;
    private final String description;

    /**
     * Creates a new addon command.
     *
     * @param name The name of the command
     * @param description A short description of what the command does
     */
    public AddonCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Helper method to create an argument for the command builder.
     *
     * @param name The name of the argument
     * @param type The type of the argument
     * @return A new argument builder
     */
    protected static <T> RequiredArgumentBuilder<SharedSuggestionProvider, T> argument(final String name, final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    /**
     * Helper method to create a literal for the command builder.
     *
     * @param name The name of the literal
     * @return A new literal builder
     */
    protected static LiteralArgumentBuilder<SharedSuggestionProvider> literal(final String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    /**
     * Registers this command to the dispatcher.
     *
     * @param dispatcher The dispatcher to register to
     */
    public void register(CommandDispatcher<SharedSuggestionProvider> dispatcher) {
        LiteralArgumentBuilder<SharedSuggestionProvider> builder = LiteralArgumentBuilder.literal(name);
        build(builder);
        dispatcher.register(builder);
    }

    /**
     * Implement this method to build the command.
     *
     * @param builder The command builder
     */
    public abstract void build(LiteralArgumentBuilder<SharedSuggestionProvider> builder);

    /**
     * Gets the command name.
     *
     * @return The command name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the command description.
     *
     * @return The command description
     */
    public String getDescription() {
        return description;
    }
} 