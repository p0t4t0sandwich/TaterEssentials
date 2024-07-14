/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.ping;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.ping.command.PingCommand;

/** Ping module. */
public class PingModule implements PluginModule {
    @Override
    public String id() {
        return "Ping";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        Command command = new PingCommand();
                        if (TaterAPIProvider.platform().isBungeeCordBased()) {
                            command.setName("bping");
                        } else if (TaterAPIProvider.platform().isVelocityBased()) {
                            command.setName("vping");
                        }
                        event.registerCommand(command);
                    }));
        }
    }
}
