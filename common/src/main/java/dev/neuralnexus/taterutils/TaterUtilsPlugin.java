package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.Plugin;

/** The main plugin interface. */
public interface TaterUtilsPlugin extends Plugin {
    @Override
    default String name() {
        return TaterUtils.Constants.PROJECT_NAME;
    }

    @Override
    default String id() {
        return TaterUtils.Constants.PROJECT_ID;
    }

    @Override
    default void pluginStart(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger) {
        logger.info(
                TaterUtils.Constants.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.serverType()
                        + " "
                        + TaterAPIProvider.minecraftVersion()
                        + "!");
        PluginEvents.DISABLED.register(event -> pluginStop());
        TaterUtils.start(plugin, pluginServer, pluginLogger, logger);
    }

    @Override
    default void pluginStop() {
        TaterUtils.stop();
    }
}
