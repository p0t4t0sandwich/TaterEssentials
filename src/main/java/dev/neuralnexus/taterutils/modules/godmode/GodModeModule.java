/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.godmode;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.godmode.command.GodModeCommand;

/** GodMode module. */
public class GodModeModule implements PluginModule {
    @Override
    public String id() {
        return "GodMode";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    event -> {
                        if (!TaterAPIProvider.platform().isProxy()) {
                            event.registerCommand(new GodModeCommand());
                        }
                    });

            // Register listeners
            EntityEvents.DAMAGE.register(
                    event -> {
                        if (event.entity() instanceof Player) {
                            if (TaterUtilsAPIProvider.get()
                                    .godModeAPI()
                                    .getGodMode((Player) event.entity())) {
                                event.setCancelled(true);
                            }
                        }
                    });
        }
    }
}
