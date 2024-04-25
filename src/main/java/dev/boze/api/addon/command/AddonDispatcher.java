package dev.boze.api.addon.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

/**
 * Interface for addon dispatchers
 *
 * All addons must implement this and use this to register commands
 *
 */
public interface AddonDispatcher {

    /**
     * @return The addon's command dispatcher
     */
    CommandDispatcher<CommandSource> getDispatcher();

    /**
     * Notice: This is not the same as the command prefix in the config, this is the prefix for the addon's commands
     * All commands must still start with the global command prefix, followed by the addon prefix (without space)
     *
     * For example, for a command "example" with addon prefix "ex", the command would be ".ex-example ..."
     *
     * @return The addon's command prefix
     */
    String getPrefix();
}
