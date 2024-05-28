/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.tpa;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.tpa.command.TpAcceptCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpDenyCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpHereCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpaCommand;

/** TPA module. */
public class TpaModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "TPA";
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
                            event.registerCommand(TaterUtils.plugin(), new TpaCommand());
                            event.registerCommand(
                                    TaterUtils.plugin(), new TpHereCommand(), "tpahere");
                            event.registerCommand(TaterUtils.plugin(), new TpAcceptCommand());
                            event.registerCommand(TaterUtils.plugin(), new TpDenyCommand());
                        }
                    }));

            // Set repeating task that clears stale TPA requests every minute
            Utils.repeatTaskAsync(
                    () -> TaterUtilsAPIProvider.get().tpaAPI().checkExpired(), 0L, 1200L);
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
