package dev.neuralnexus.taterutils.sponge;

import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.sponge.logger.SpongeLogger;
import dev.neuralnexus.taterutils.common.Constants;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;
import com.google.inject.Inject;
import org.apache.logging.log4j.Logger;

/**
 * Sponge entry point.
 */
@Plugin(Constants.PROJECT_ID)
public class SpongePlugin implements TaterUtilsPlugin {
    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    public SpongePlugin() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new SpongeLogger(logger));
    }
}
