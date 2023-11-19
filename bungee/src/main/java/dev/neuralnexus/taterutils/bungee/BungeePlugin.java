package dev.neuralnexus.taterutils.bungee;

import dev.neuralnexus.taterlib.bungee.logger.BungeeLogger;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeePlugin extends Plugin implements TaterUtilsPlugin {
    public BungeePlugin() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new BungeeLogger(getLogger()));
    }
}
