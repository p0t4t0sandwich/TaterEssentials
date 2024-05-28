/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils;

import com.google.common.collect.ImmutableMap;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.api.info.ServerType;
import dev.neuralnexus.taterlib.bstats.MetricsAdapter;
import dev.neuralnexus.taterlib.event.api.PluginEvents;
import dev.neuralnexus.taterlib.logger.AbstractLogger;
import dev.neuralnexus.taterlib.plugin.ModuleLoader;
import dev.neuralnexus.taterlib.plugin.Plugin;
import dev.neuralnexus.taterutils.api.TaterUtilsAPI;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfig;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.modules.alert.AlertModule;
import dev.neuralnexus.taterutils.modules.badspawns.BadSpawnsModule;
import dev.neuralnexus.taterutils.modules.chatformatter.ChatFormatterModule;
import dev.neuralnexus.taterutils.modules.gamemode.GameModeModule;
import dev.neuralnexus.taterutils.modules.godmode.GodModeModule;
import dev.neuralnexus.taterutils.modules.home.HomeModule;
import dev.neuralnexus.taterutils.modules.joinandquit.JoinAndQuitModule;
import dev.neuralnexus.taterutils.modules.motd.MotdModule;
import dev.neuralnexus.taterutils.modules.orewatcher.OreWatcherModule;
import dev.neuralnexus.taterutils.modules.ping.PingModule;
import dev.neuralnexus.taterutils.modules.send.SendModule;
import dev.neuralnexus.taterutils.modules.slashlobby.SlashLobbyModule;
import dev.neuralnexus.taterutils.modules.spawn.SpawnModule;
import dev.neuralnexus.taterutils.modules.tpa.TpaModule;
import dev.neuralnexus.taterutils.modules.warp.WarpModule;

/** Main class for the plugin. */
public class TaterUtils implements Plugin {
    public static final String PROJECT_NAME = "TaterUtils";
    public static final String PROJECT_ID = "taterutils";
    public static final String PROJECT_VERSION = "0.1.0-SNAPSHOT";
    public static final String PROJECT_AUTHORS = "p0t4t0sandwich, DJjewl";
    public static final String PROJECT_DESCRIPTION =
            "An essential, cross API server utility plugin that doubles as a staging ground for new plugin ideas.";
    public static final String PROJECT_URL = "https://github.com/p0t4t0sandwich/TaterUtils";

    private static final TaterUtils instance = new TaterUtils();
    private static boolean STARTED = false;
    private static boolean RELOADED = false;
    private static ModuleLoader moduleLoader;
    private Object plugin;
    private Object pluginServer;
    private Object pluginLogger;
    private AbstractLogger logger;

    /**
     * Get if the plugin has reloaded
     *
     * @return If the plugin has reloaded
     */
    public static boolean hasReloaded() {
        return RELOADED;
    }

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

    /** Reload */
    public void reload() {
        if (!STARTED) {
            logger().info(PROJECT_NAME + " has not been started!");
            return;
        }
        RELOADED = true;
        pluginStop();
        pluginStart(plugin, pluginServer, pluginLogger, logger);
        logger().info(PROJECT_NAME + " has been reloaded!");
    }

    @Override
    public String name() {
        return TaterUtils.PROJECT_NAME;
    }

    @Override
    public String id() {
        return TaterUtils.PROJECT_ID;
    }

    @Override
    public void pluginStart(
            Object plugin, Object pluginServer, Object pluginLogger, AbstractLogger logger) {
        logger.info(
                TaterUtils.PROJECT_NAME
                        + " is running on "
                        + TaterAPIProvider.serverType()
                        + " "
                        + TaterAPIProvider.minecraftVersion()
                        + "!");
        PluginEvents.DISABLED.register(event -> pluginStop());

        if (pluginServer != null) {
            setPluginServer(pluginServer);
        }
        if (pluginLogger != null) {
            setPluginLogger(pluginLogger);
        }
        setPlugin(plugin);
        setLogger(logger);

        // Set up bStats
        MetricsAdapter.setupMetrics(
                plugin,
                pluginServer,
                pluginLogger,
                ImmutableMap.<ServerType, Integer>builder()
                        .put(ServerType.BUKKIT, 21134)
                        .put(ServerType.BUNGEECORD, 21135)
                        .put(ServerType.SPONGE, 21136)
                        .put(ServerType.VELOCITY, 21137)
                        .build());

        // Config
        TaterUtilsConfigLoader.load();

        if (STARTED) {
            logger().info(PROJECT_NAME + " has already started!");
            return;
        }
        STARTED = true;

        // Register API
        TaterUtilsAPIProvider.register(new TaterUtilsAPI());

        if (!RELOADED) {
            // Register modules
            moduleLoader = new TaterUtilsModuleLoader();
            TaterUtilsConfig config = TaterUtilsConfigLoader.config();

            if (config.checkModule("alert")) {
                moduleLoader.registerModule(new AlertModule());
            }
            if (config.checkModule("badSpawns")) {
                moduleLoader.registerModule(new BadSpawnsModule());
            }
            if (config.checkModule("chatFormatter")) {
                moduleLoader.registerModule(new ChatFormatterModule());
            }
            if (config.checkModule("gameMode")) {
                moduleLoader.registerModule(new GameModeModule());
            }
            if (config.checkModule("godMode")) {
                moduleLoader.registerModule(new GodModeModule());
            }
            if (config.checkModule("home")) {
                moduleLoader.registerModule(new HomeModule());
            }
            if (config.checkModule("joinAndQuit")) {
                moduleLoader.registerModule(new JoinAndQuitModule());
            }
            if (config.checkModule("motd")) {
                moduleLoader.registerModule(new MotdModule());
            }
            if (config.checkModule("oreWatcher")) {
                moduleLoader.registerModule(new OreWatcherModule());
            }
            if (config.checkModule("ping")) {
                moduleLoader.registerModule(new PingModule());
            }
            if (config.checkModule("send")) {
                moduleLoader.registerModule(new SendModule());
            }
            if (config.checkModule("slashLobby")) {
                moduleLoader.registerModule(new SlashLobbyModule());
            }
            if (config.checkModule("spawn")) {
                moduleLoader.registerModule(new SpawnModule());
            }
            if (config.checkModule("tpa")) {
                moduleLoader.registerModule(new TpaModule());
            }
            if (config.checkModule("warp")) {
                moduleLoader.registerModule(new WarpModule());
            }
        }

        // Start modules
        logger().info("Starting modules: " + moduleLoader.moduleNames());
        moduleLoader.startModules();

        logger().info(PROJECT_NAME + " has been started!");
    }

    @Override
    public void pluginStop() {
        if (!STARTED) {
            logger().info(PROJECT_NAME + " has already stopped!");
            return;
        }
        STARTED = false;
        RELOADED = true;

        // Stop modules
        logger().info("Stopping modules: " + moduleLoader.moduleNames());
        moduleLoader.stopModules();

        // Unregister API
        TaterUtilsAPIProvider.unregister();

        // Unload config
        TaterUtilsConfigLoader.unload();

        logger().info(PROJECT_NAME + " has been stopped!");
    }
}
