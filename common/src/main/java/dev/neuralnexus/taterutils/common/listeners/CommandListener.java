package dev.neuralnexus.taterutils.common.listeners;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.commands.HomeCommand;

/**
 * The command listener.
 */
public class CommandListener {
    /**
     * Called when commands are being registered.
     * @param event The event.
     */
    public static void onRegisterCommand(CommandRegisterEvent event) {
        if (!(TaterAPIProvider.get().serverType().isBungeeCordBased() || TaterAPIProvider.get().serverType().isVelocityBased())) {
            event.registerCommand(TaterUtils.getPlugin(), new HomeCommand());
        }
    }
}
