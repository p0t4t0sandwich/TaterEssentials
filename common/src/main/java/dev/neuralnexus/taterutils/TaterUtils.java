package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterutils.api.TaterUtilsAPI;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.alert.AlertModule;
import dev.neuralnexus.taterutils.modules.badspawns.BadSpawnsModule;
import dev.neuralnexus.taterutils.modules.chatformatter.ChatFormatterModule;
import dev.neuralnexus.taterutils.modules.gamemode.GameModeModule;
import dev.neuralnexus.taterutils.modules.godmode.GodModeModule;
import dev.neuralnexus.taterutils.modules.home.HomeModule;
import dev.neuralnexus.taterutils.modules.joinandquit.JoinAndQuitModule;
import dev.neuralnexus.taterutils.modules.mclogs.MCLogsModule;
import dev.neuralnexus.taterutils.modules.motd.MotdModule;
import dev.neuralnexus.taterutils.modules.orewatcher.OreWatcherModule;
import dev.neuralnexus.taterutils.modules.ping.PingModule;
import dev.neuralnexus.taterutils.modules.send.SendModule;
import dev.neuralnexus.taterutils.modules.slashlobby.SlashLobbyModule;
import dev.neuralnexus.taterutils.modules.spawn.SpawnModule;
import dev.neuralnexus.taterutils.modules.tpa.TpaModule;
import dev.neuralnexus.taterutils.modules.warp.WarpModule;

/** Main class for the plugin. */
public class TaterUtils {
    private static final TaterUtils instance = new TaterUtils();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static ModuleLoader moduleLoader;
    private Object plugin;
    private Object pluginServer;
    private Object pluginLogger;
    private AbstractLogger logger;

    /**
     * Getter for the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static TaterUtils instance() {
        return instance;
    }

    /**
     * Get the plugin
     *
     * @return The plugin
     */
    public static Object plugin() {
        return instance.plugin;
    }

    /**
     * Set the plugin
     *
     * @param plugin The plugin
     */
    private static void setPlugin(Object plugin) {
        instance.plugin = plugin;
    }

    /**
     * Set the plugin server
     *
     * @param pluginServer The plugin server
     */
    private static void setPluginServer(Object pluginServer) {
        instance.pluginServer = pluginServer;
    }

    /**
     * Set the plugin logger
     *
     * @param pluginLogger The plugin logger
     */
    private static void setPluginLogger(Object pluginLogger) {
        instance.pluginLogger = pluginLogger;
    }

    /**
     * Get the logger
     *
     * @return The logger
     */
    public static AbstractLogger logger() {
        return instance.logger;
    }

    /**
     * Set the logger
     *
     * @param logger The logger
     */
    private static void setLogger(AbstractLogger logger) {
        instance.logger = logger;
    }

    /**
     * Start
     *
     * @param plugin The plugin
     * @param pluginServer The plugin server
     * @param pluginLogger The plugin logger
     * @param logger The logger
     */
    public static void start(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger) {
        if (pluginServer != null) {
            setPluginServer(pluginServer);
        }
        if (pluginLogger != null) {
            setPluginLogger(pluginLogger);
        }
        setPlugin(plugin);
        setLogger(logger);

        // Set up bStats
        //        MetricsAdapter.setupMetrics(
        //                plugin,
        //                pluginServer,
        //                pluginLogger,
        //                ImmutableMap.<ServerType, Integer>builder()
        //                        .put(ServerType.BUKKIT, 21008)
        //                        .put(ServerType.BUNGEECORD, 21009)
        //                        .put(ServerType.SPONGE, 21010)
        //                        .put(ServerType.VELOCITY, 21011)
        //                        .build());

        // Config
        TaterUtilsConfig.loadConfig(TaterAPIProvider.serverType().dataFolders().configFolder());

        if (STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        // Register API
        TaterUtilsAPIProvider.register(new TaterUtilsAPI());

        if (!RELOADED) {
            // Register modules
            moduleLoader = new TaterUtilsModuleLoader();
            if (TaterUtilsConfig.isModuleEnabled("alert")) {
                moduleLoader.registerModule(new AlertModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("badSpawns")) {
                moduleLoader.registerModule(new BadSpawnsModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("chatFormatter")) {
                moduleLoader.registerModule(new ChatFormatterModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("gameMode")) {
                moduleLoader.registerModule(new GameModeModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("godMode")) {
                moduleLoader.registerModule(new GodModeModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("home")) {
                moduleLoader.registerModule(new HomeModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("joinAndQuit")) {
                moduleLoader.registerModule(new JoinAndQuitModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("mclogs")) {
                moduleLoader.registerModule(new MCLogsModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("motd")) {
                moduleLoader.registerModule(new MotdModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("oreWatcher")) {
                moduleLoader.registerModule(new OreWatcherModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("ping")) {
                moduleLoader.registerModule(new PingModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("send")) {
                moduleLoader.registerModule(new SendModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("slashLobby")) {
                moduleLoader.registerModule(new SlashLobbyModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("spawn")) {
                moduleLoader.registerModule(new SpawnModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("tpa")) {
                moduleLoader.registerModule(new TpaModule());
            }
            if (TaterUtilsConfig.isModuleEnabled("warp")) {
                moduleLoader.registerModule(new WarpModule());
            }
        }

        // Start modules
        moduleLoader.startModules();

        instance.logger.info(Constants.PROJECT_NAME + " has been started!");
    }

    /** Start */
    public static void start() {
        start(instance.plugin, instance.pluginServer, instance.pluginLogger, instance.logger);
    }

    /** Stop */
    public static void stop() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;
        RELOADED = true;

        // Stop modules
        moduleLoader.stopModules();

        // Remove references to objects
        TaterUtilsConfig.unloadConfig();

        instance.logger.info(Constants.PROJECT_NAME + " has been stopped!");
    }

    /** Reload */
    public static void reload() {
        if (!STARTED) {
            instance.logger.info(Constants.PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Unregister API
        TaterUtilsAPIProvider.unregister();

        // Start
        start();

        instance.logger.info(Constants.PROJECT_NAME + " has been reloaded!");
    }

    /** Constants used throughout the plugin. */
    public static class Constants {
        public static final String PROJECT_NAME = "TaterUtils";
        public static final String PROJECT_ID = "taterutils";
        public static final String PROJECT_VERSION = "0.1.0-R0.2-SNAPSHOT";
        public static final String PROJECT_AUTHORS = "p0t4t0sandwich, DJjewl";
        public static final String PROJECT_DESCRIPTION =
                "An essential, cross API server utility plugin that doubles as a staging ground for new plugin ideas.";
        public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterUtils";
    }
}
