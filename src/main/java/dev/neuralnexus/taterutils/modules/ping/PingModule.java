package dev.neuralnexus.taterutils.modules.ping;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.ping.command.PingCommand;

/** Ping module. */
public class PingModule implements PluginModule {
    private static final boolean RELOADED = false;
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "Ping";
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
                        Command command = new PingCommand();
                        if (TaterAPIProvider.serverType().isBungeeCordBased()) {
                            command.setName("bping");
                        } else if (TaterAPIProvider.serverType().isVelocityBased()) {
                            command.setName("vping");
                        }
                        event.registerCommand(TaterUtils.plugin(), command);
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

        // Remove references to objects

        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
