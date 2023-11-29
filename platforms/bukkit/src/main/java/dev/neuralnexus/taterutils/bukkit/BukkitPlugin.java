package dev.neuralnexus.taterutils.bukkit;

import dev.neuralnexus.taterlib.logger.GenericLogger;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin implements TaterUtilsPlugin {
    public BukkitPlugin() {
        pluginStart(
                this,
                new GenericLogger(
                        "[" + TaterUtils.Constants.PROJECT_NAME + "] ",
                        TaterUtils.Constants.PROJECT_ID));
    }
}
