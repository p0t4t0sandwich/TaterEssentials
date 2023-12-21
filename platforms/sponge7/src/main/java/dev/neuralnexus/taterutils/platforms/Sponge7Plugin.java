package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import org.slf4j.Logger;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = TaterUtils.Constants.PROJECT_ID,
        name = TaterUtils.Constants.PROJECT_NAME,
        version = TaterUtils.Constants.PROJECT_VERSION,
        description = TaterUtils.Constants.PROJECT_DESCRIPTION)
public class Sponge7Plugin implements TaterUtilsPlugin {
    @Inject
    public Sponge7Plugin(PluginContainer container, Logger logger) {
        pluginStart(container, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME, logger));
    }
}
