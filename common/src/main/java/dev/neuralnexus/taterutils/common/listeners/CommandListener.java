package dev.neuralnexus.taterutils.common.listeners;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.commands.home.HomeCommand;
import dev.neuralnexus.taterutils.common.commands.home.SetHomeCommand;
import dev.neuralnexus.taterutils.common.commands.warp.DelWarpCommand;
import dev.neuralnexus.taterutils.common.commands.warp.SetWarpCommand;
import dev.neuralnexus.taterutils.common.commands.warp.WarpCommand;

/**
 * The command listener.
 */
public class CommandListener {
    /**
     * Called when commands are being registered.
     * @param event The event.
     */
    public static void onRegisterCommand(CommandRegisterEvent event) {
        if (!TaterAPIProvider.get().serverType().isProxy()) {
            // Home
            event.registerCommand(TaterUtils.getPlugin(), new HomeCommand());
            event.registerCommand(TaterUtils.getPlugin(), new SetHomeCommand(), "addhome");
            event.registerCommand(TaterUtils.getPlugin(), new DelWarpCommand(), "deletehome", "removehome", "rmhome");

            // Warp
            event.registerCommand(TaterUtils.getPlugin(), new WarpCommand(), "warps");
            event.registerCommand(TaterUtils.getPlugin(), new SetWarpCommand(), "addwarp");
            event.registerCommand(TaterUtils.getPlugin(), new DelWarpCommand(), "deletewarp", "removewarp", "rmwarp");
        }
    }
}
