package dev.neuralnexus.taterutils.common;

import dev.neuralnexus.taterlib.common.Plugin;
import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;

/**
 * Main class for the plugin.
 */
public class TaterUtils {
    private static final TaterUtils instance = new TaterUtils();
    private static Plugin plugin;
    public static AbstractLogger logger;
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    /**
     * Getter for the singleton instance of the class.
     * @return The singleton instance
     */
    public static TaterUtils getInstance() {
        return instance;
    }

    /**
     * Start
     * @param plugin The plugin
     * @param logger The logger
     */
    public static void start(Plugin plugin, AbstractLogger logger) {
        TaterUtils.plugin = plugin;
        TaterUtils.logger = logger;

        // Config
        TaterUtilsConfig.loadConfig(TaterLib.configFolder);

        if (STARTED) {
            logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register events
        }

        logger.info(Constants.PROJECT_NAME + " has been started!");
        TaterUtilsAPIProvider.register(instance);
    }

    /**
     * Start
     */
    public static void start() {
        start(plugin, logger);
    }

    /**
     * Stop
     */
    public static void stop() {
        if (!STARTED) {
            logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects
        TaterUtilsConfig.unloadConfig();

        logger.info(Constants.PROJECT_NAME + " has been stopped!");
        TaterUtilsAPIProvider.unregister();
    }

    /**
     * Reload
     */
    public static void reload() {
        if (!STARTED) {
            logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start(plugin, logger);

        logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }
}
