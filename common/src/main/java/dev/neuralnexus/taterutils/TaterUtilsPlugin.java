package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** The main plugin interface. */
public interface TaterUtilsPlugin extends Plugin {
    /** Starts the plugin. */
    default void pluginStart(Object plugin, AbstractLogger logger) {
        logger.info(
                TaterUtils.Constants.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.get().serverType()
                        + " "
                        + TaterAPIProvider.get().minecraftVersion()
                        + "!");
        PluginEvents.DISABLED.register(event -> pluginStop());
        TaterUtils.start(plugin, logger);
    }

    /** Stops the plugin. */
    default void pluginStop() {
        TaterUtils.stop();
    }
}
