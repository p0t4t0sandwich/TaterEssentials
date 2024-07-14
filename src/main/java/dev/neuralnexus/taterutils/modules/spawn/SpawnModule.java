/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.spawn;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.spawn.command.DelSpawnCommand;
import dev.neuralnexus.taterutils.modules.spawn.command.SetSpawnCommand;
import dev.neuralnexus.taterutils.modules.spawn.command.SpawnCommand;

/** Spawn module. */
public class SpawnModule implements PluginModule {
    @Override
    public String id() {
        return "Spawn";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        if (!TaterAPIProvider.platform().isProxy()) {
                            event.registerCommand(new SpawnCommand());
                            event.registerCommand(new SetSpawnCommand(), "addspawn", "createspawn");
                            event.registerCommand(
                                    new DelSpawnCommand(), "deletespawn", "removespawn", "rmspawn");
                        }
                    }));
        }
    }
}
