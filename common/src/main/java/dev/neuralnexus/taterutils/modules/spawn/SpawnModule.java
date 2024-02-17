package dev.neuralnexus.taterutils.modules.spawn;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.spawn.command.DelSpawnCommand;
import dev.neuralnexus.taterutils.modules.spawn.command.SetSpawnCommand;
import dev.neuralnexus.taterutils.modules.spawn.command.SpawnCommand;

/** Spawn module. */
public class SpawnModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "Spawn";
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
                        if (!TaterAPIProvider.serverType().isProxy()) {
                            event.registerCommand(TaterUtils.plugin(), new SpawnCommand());
                            event.registerCommand(
                                    TaterUtils.plugin(),
                                    new SetSpawnCommand(),
                                    "addspawn",
                                    "createspawn");
                            event.registerCommand(
                                    TaterUtils.plugin(),
                                    new DelSpawnCommand(),
                                    "deletespawn",
                                    "removespawn",
                                    "rmspawn");
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

    @Override
    public void reload() {
        if (!STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        TaterUtils.logger().info("Submodule " + name() + " has been reloaded!");
    }
}
