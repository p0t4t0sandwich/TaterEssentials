/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.tpa;

import static dev.neuralnexus.taterapi.util.ScheduleUtil.repeatTaskAsync;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.tpa.command.TpAcceptCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpDenyCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpHereCommand;
import dev.neuralnexus.taterutils.modules.tpa.command.TpaCommand;

/** TPA module. */
public class TpaModule implements PluginModule {
    @Override
    public String id() {
        return "TPA";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        if (!TaterAPIProvider.platform().isProxy()) {
                            event.registerCommand(new TpaCommand());
                            event.registerCommand(new TpHereCommand(), "tpahere");
                            event.registerCommand(new TpAcceptCommand());
                            event.registerCommand(new TpDenyCommand());
                        }
                    }));

            // Set repeating task that clears stale TPA requests every minute
            repeatTaskAsync(() -> TaterUtilsAPIProvider.get().tpaAPI().checkExpired(), 0L, 1200L);
        }
    }
}
