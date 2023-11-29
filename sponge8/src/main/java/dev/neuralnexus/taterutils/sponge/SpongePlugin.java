package dev.neuralnexus.taterutils.sponge;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.logger.GenericLogger;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;

import org.apache.logging.log4j.Logger;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterUtils.Constants.PROJECT_ID)
public class SpongePlugin implements TaterUtilsPlugin {
    @Inject
    public SpongePlugin(PluginContainer container, Logger logger) {
        pluginStart(container, new GenericLogger(
                "[" + TaterUtils.Constants.PROJECT_NAME + "] ",
                TaterUtils.Constants.PROJECT_ID));
    }
}
