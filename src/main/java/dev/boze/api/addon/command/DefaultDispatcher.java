package dev.boze.api.addon.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

public class DefaultDispatcher implements AddonDispatcher {

    private final CommandDispatcher<CommandSource> dispatcher = new CommandDispatcher<>();

    private final String prefix;

    public DefaultDispatcher(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public CommandDispatcher<CommandSource> getDispatcher() {
        return dispatcher;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }
}
