package dev.neuralnexus.taterutils.modules.slashlobby;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsConfigOld;
import dev.neuralnexus.taterutils.modules.slashlobby.command.SlashLobbyCommand;

/** SlashLobby module. */
public class SlashLobbyModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "SlashLobby";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        if (TaterAPIProvider.serverType().isProxy()) {
                            event.registerCommand(
                                    TaterUtils.plugin(),
                                    new SlashLobbyCommand(),
                                    TaterUtilsConfigOld.SlashLobbyConfig.getLobbyNames());
                        }
                    }));
        }

        TaterUtils.logger().info("Submodule " + name() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;
        RELOADED = true;

        // Remove references to objects

        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
