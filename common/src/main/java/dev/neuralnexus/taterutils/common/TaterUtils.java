package dev.neuralnexus.taterutils.common;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.logger.AbstractLogger;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPI;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;

/**
 * Main class for the plugin.
 */
public class TaterUtils {
    private static final TaterUtils instance = new TaterUtils();
    private Object plugin;
    private AbstractLogger logger;
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
     * Set the plugin
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Get the plugin
     * @return The plugin
     */
    public static Object getPlugin() {
        return instance.plugin;
    }

    /**
     * Set the logger
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /**
     * Get the logger
     * @return The logger
     */
    public static AbstractLogger getLogger() {
        return instance.logger;
    }

    /**
     * Start
     * @param plugin The plugin
     * @param logger The logger
     */
    public static void start(Object plugin, AbstractLogger logger) {
        setPlugin(plugin);
        setLogger(logger);

        // Config
        TaterUtilsConfig.loadConfig(TaterAPIProvider.get().configFolder());

        if (STARTED) {
            logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        // Start modules
        TaterUtilsModuleLoader.startModules();

        logger.info(Constants.PROJECT_NAME + " has been started!");

        TaterUtilsAPIProvider.register(new TaterUtilsAPI());
    }

    /**
     * Start
     */
    public static void start() {
        start(instance.plugin, instance.logger);
    }

    /**
     * Stop
     */
    public static void stop() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;

        // Stop modules
        TaterUtilsModuleLoader.stopModules();

        // Remove references to objects
        TaterUtilsConfig.unloadConfig();

        instance.logger.info(Constants.PROJECT_NAME + " has been stopped!");
        TaterUtilsAPIProvider.unregister();
    }

    /**
     * Reload
     */
    public static void reload() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        instance.logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /**
     * Constants used throughout the plugin.
     */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterUtils";
        public static final String PROJECT_ID = "taterutils";
        public static final String PROJECT_VERSION = "0.1.0-R0.1-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich";
        public static final String PROJECT_DESCRIPTION = "An essential, cross API server utility plugin that doubles as a staging ground for new plugin ideas.";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterUtils";
    }
}
