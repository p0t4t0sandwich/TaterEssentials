package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterutils.api.TaterUtilsAPI;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.home.HomeModule;
import dev.neuralnexus.taterutils.modules.joinandquit.JoinAndQuitModule;
import dev.neuralnexus.taterutils.modules.mclogs.MCLogsModule;
import dev.neuralnexus.taterutils.modules.motd.MotdModule;
import dev.neuralnexus.taterutils.modules.orewatcher.OreWatcherModule;
import dev.neuralnexus.taterutils.modules.send.SendModule;
import dev.neuralnexus.taterutils.modules.slashlobby.SlashLobbyModule;
import dev.neuralnexus.taterutils.modules.spawn.SpawnModule;
import dev.neuralnexus.taterutils.modules.tpa.TpaModule;
import dev.neuralnexus.taterutils.modules.warp.WarpModule;

/** Main class for the plugin. */
public class TaterUtils {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static Object plugin;
    private static AbstractLogger logger;

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    public static Object getPlugin() {
        return plugin;
    }

    /**
     * Set the plugin
     *
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        TaterUtils.plugin = plugin;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static AbstractLogger getLogger() {
        return logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        TaterUtils.logger = logger;
    }

    /**
     * Start
     *
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

        // Register API
        TaterUtilsAPIProvider.register(new TaterUtilsAPI());

        if (!RELOADED) {
            // Register modules
            if (TaterUtilsConfig.isModuleEnabled("home")) {
                TaterUtilsModuleLoader.registerModule(new HomeModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("joinAndQuit")) {
                TaterUtilsModuleLoader.registerModule(new JoinAndQuitModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("mclogs")) {
                TaterUtilsModuleLoader.registerModule(new MCLogsModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("motd")) {
                TaterUtilsModuleLoader.registerModule(new MotdModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("oreWatcher")) {
                TaterUtilsModuleLoader.registerModule(new OreWatcherModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("send")) {
                TaterUtilsModuleLoader.registerModule(new SendModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("slashLobby")) {
                TaterUtilsModuleLoader.registerModule(new SlashLobbyModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("spawn")) {
                TaterUtilsModuleLoader.registerModule(new SpawnModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("tpa")) {
                TaterUtilsModuleLoader.registerModule(new TpaModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("warp")) {
                TaterUtilsModuleLoader.registerModule(new WarpModule());
            }
        }

        // Start modules
        TaterUtilsModuleLoader.startModules();

        logger.info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;
        RELOADED = true;

        // Stop modules
        TaterUtilsModuleLoader.stopModules();

        // Remove references to objects
        TaterUtilsConfig.unloadConfig();

        logger.info(Constants.PROJECT_NAME + " has been stopped!");
    }

    /** Reload */
    public static void reload() {
        if (!STARTED) {
            logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Unregister API
        TaterUtilsAPIProvider.unregister();

        // Start
        start(plugin, logger);

        logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterUtils";
        public static final String PROJECT_ID = "taterutils";
        public static final String PROJECT_VERSION = "0.1.0-R0.1-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich, DJjewl";
        public static final String PROJECT_DESCRIPTION =
                "An essential, cross API server utility plugin that doubles as a staging ground for new plugin ideas.";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterUtils";
    }
}
