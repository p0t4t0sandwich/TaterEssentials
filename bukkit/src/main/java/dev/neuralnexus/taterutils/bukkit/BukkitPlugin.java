package dev.neuralnexus.taterutils.bukkit;

import dev.neuralnexus.taterlib.bukkit.logger.BukkitLogger;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Bukkit entry point.
 */
public class BukkitPlugin extends JavaPlugin implements TaterUtilsPlugin {
    @Override
    public void onEnable() {
        pluginStart(this, new BukkitLogger(getLogger()));
    }

    @Override
    public void onDisable() {
        pluginStop();
    }
}
