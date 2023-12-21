package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin implements TaterUtilsPlugin {
    public BukkitPlugin() {
        pluginStart(this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME, getLogger()));
    }
}
