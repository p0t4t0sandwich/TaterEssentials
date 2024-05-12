package dev.neuralnexus.taterutils.modules.orewatcher;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.event.api.BlockEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.orewatcher.listeners.OreWatcherBlockListener;

/** OreWatcher module. */
public class OreWatcherModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "OreWatcher";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register block events
            BlockEvents.PLAYER_BLOCK_BREAK.register(OreWatcherBlockListener::onBlockBreak);

            // Reset the average per minute every 30 minutes
            Utils.repeatTaskAsync(
                    () -> TaterUtilsAPIProvider.get().oreWatcherAPI().resetAveragePerMinute(),
                    0L,
                    36000L);
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
}
