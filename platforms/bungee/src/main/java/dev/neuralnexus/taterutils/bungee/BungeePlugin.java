package dev.neuralnexus.taterutils.bungee;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeePlugin extends Plugin implements TaterUtilsPlugin {
    public BungeePlugin() {
        pluginStart(this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME, getLogger()));
    }
}
