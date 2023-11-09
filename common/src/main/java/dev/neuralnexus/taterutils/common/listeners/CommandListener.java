package dev.neuralnexus.taterutils.common.listeners;

import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.commands.HomeCommand;
import dev.neuralnexus.taterutils.common.commands.SetWarpCommand;
import dev.neuralnexus.taterutils.common.commands.WarpCommand;

/**
 * The command listener.
 */
public class CommandListener {
    /**
     * Called when commands are being registered.
     * @param event The event.
     */
    public static void onRegisterCommand(CommandRegisterEvent event) {
        event.registerCommand(TaterUtils.getPlugin(), new HomeCommand());
        event.registerCommand(TaterUtils.getPlugin(), new WarpCommand());
        event.registerCommand(TaterUtils.getPlugin(), new SetWarpCommand());
    }
}
