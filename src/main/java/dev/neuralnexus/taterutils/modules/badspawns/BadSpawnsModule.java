/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.badspawns;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.event.api.EntityEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.badspawns.api.BadSpawnsAPI;

/** BadSpawns module. */
public class BadSpawnsModule implements PluginModule {
    @Override
    public String id() {
        return "BadSpawns";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            if (!TaterAPIProvider.platform().isProxy()) {
                // Register listeners
                EntityEvents.SPAWN.register(
                        event -> {
                            BadSpawnsAPI api = TaterUtilsAPIProvider.get().badSpawnsAPI();
                            Entity entity = event.entity();
                            if (api.isBannedGlobally(entity) || !api.canSpawn(entity)) {
                                event.setCancelled(true);
                            }
                        });
            }
        }
    }
}
