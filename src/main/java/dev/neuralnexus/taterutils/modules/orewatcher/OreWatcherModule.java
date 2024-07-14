/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.orewatcher;

import static dev.neuralnexus.taterapi.util.ScheduleUtil.repeatTaskAsync;

import dev.neuralnexus.taterapi.event.api.BlockEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.orewatcher.listeners.OreWatcherBlockListener;

/** OreWatcher module. */
public class OreWatcherModule implements PluginModule {
    @Override
    public String id() {
        return "OreWatcher";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register block events
            BlockEvents.PLAYER_BLOCK_BREAK.register(OreWatcherBlockListener::onBlockBreak);

            // Reset the average per minute every 30 minutes
            repeatTaskAsync(
                    () -> TaterUtilsAPIProvider.get().oreWatcherAPI().resetAveragePerMinute(),
                    0L,
                    36000L);
        }
        TaterUtils.logger().info("Submodule " + id() + " has been started!");
    }
}
