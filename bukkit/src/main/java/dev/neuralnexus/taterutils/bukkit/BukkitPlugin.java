package dev.neuralnexus.taterutils.bukkit;

import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin implements TaterUtilsPlugin {
    public BukkitPlugin() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new BukkitLogger(getLogger()));
    }
}
