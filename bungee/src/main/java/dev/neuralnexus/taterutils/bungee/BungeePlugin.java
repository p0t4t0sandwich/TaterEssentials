package dev.neuralnexus.taterutils.bungee;

import dev.neuralnexus.taterlib.bungee.logger.BungeeLogger;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Bungee entry point.
 */
public class BungeePlugin extends Plugin implements TaterUtilsPlugin {
    @Override
    public void onEnable() {
        pluginStart(this, new BungeeLogger(getLogger()));
    }

    @Override
    public void onDisable() {
        pluginStop();
    }
}
