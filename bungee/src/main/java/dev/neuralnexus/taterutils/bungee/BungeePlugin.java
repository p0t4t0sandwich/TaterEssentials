package dev.neuralnexus.taterutils.bungee;

import dev.neuralnexus.taterlib.bungee.logger.BungeeLogger;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeePlugin extends Plugin implements TaterUtilsPlugin {
    public BungeePlugin() {
        pluginStart(this, new BungeeLogger(getLogger()));
    }
}
