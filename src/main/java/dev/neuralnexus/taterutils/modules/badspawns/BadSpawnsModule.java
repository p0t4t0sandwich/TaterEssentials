/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.badspawns;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.api.EntityEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.badspawns.api.BadSpawnsAPI;

/** BadSpawns module. */
public class BadSpawnsModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "BadSpawns";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            if (!TaterAPIProvider.serverType().isProxy()) {
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
