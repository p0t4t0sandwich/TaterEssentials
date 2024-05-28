/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

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
        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
