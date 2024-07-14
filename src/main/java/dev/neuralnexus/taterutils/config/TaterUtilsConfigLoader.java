/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.config;

import static dev.neuralnexus.taterlib.config.ConfigUtil.*;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.config.sections.*;
import dev.neuralnexus.taterutils.config.versions.TaterUtilsConfig_V1;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** A class for loading Switchboard configuration. */
public class TaterUtilsConfigLoader {
    private static final Path configPath =
            Paths.get(
                    TaterAPIProvider.platformData().configFolder()
                            + File.separator
                            + TaterUtils.PROJECT_ID
                            + File.separator
                            + TaterUtils.PROJECT_ID
                            + ".conf");
    private static final String defaultConfigPath = "source." + TaterUtils.PROJECT_ID + ".conf";
    private static final TypeToken<Integer> versionType = new TypeToken<Integer>() {};
    private static final TypeToken<List<ModuleConfig>> moduleType =
            new TypeToken<List<ModuleConfig>>() {};
    private static final TypeToken<BadSpawnsConfig> badSpawnsType =
            new TypeToken<BadSpawnsConfig>() {};
    private static final TypeToken<ChatFormatterConfig> chatFormatterType =
            new TypeToken<ChatFormatterConfig>() {};
    private static final TypeToken<GameModeConfig> gameModeType =
            new TypeToken<GameModeConfig>() {};
    private static final TypeToken<GodModeConfig> godModeType = new TypeToken<GodModeConfig>() {};
    private static final TypeToken<HomeConfig> homeType = new TypeToken<HomeConfig>() {};
    private static final TypeToken<JoinAndQuitConfig> joinAndQuitType =
            new TypeToken<JoinAndQuitConfig>() {};
    private static final TypeToken<MOTDConfig> motdType = new TypeToken<MOTDConfig>() {};
    private static final TypeToken<OreWatcherConfig> oreWatcherType =
            new TypeToken<OreWatcherConfig>() {};
    private static final TypeToken<PingConfig> pingType = new TypeToken<PingConfig>() {};
    private static final TypeToken<SlashLobbyConfig> slashLobbyType =
            new TypeToken<SlashLobbyConfig>() {};
    private static final TypeToken<SpawnConfig> spawnType = new TypeToken<SpawnConfig>() {};
    private static TaterUtilsConfig config;

    /** Load the configuration from the file. */
    public static void load() {
        copyDefaults(TaterUtils.class, configPath, defaultConfigPath, TaterUtils.logger());

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = getRoot(loader, TaterUtils.logger());
        if (root == null) {
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);

        List<ModuleConfig> modules = get(root, moduleType, "modules", TaterUtils.logger());
        BadSpawnsConfig badSpawns = get(root, badSpawnsType, "badSpawns", TaterUtils.logger());
        ChatFormatterConfig chatFormatter =
                get(root, chatFormatterType, "chatFormatter", TaterUtils.logger());
        GameModeConfig gameMode = get(root, gameModeType, "gameMode", TaterUtils.logger());
        GodModeConfig godMode = get(root, godModeType, "godMode", TaterUtils.logger());
        HomeConfig home = get(root, homeType, "home", TaterUtils.logger());
        JoinAndQuitConfig joinAndQuit =
                get(root, joinAndQuitType, "joinAndQuit", TaterUtils.logger());
        MOTDConfig motd = get(root, motdType, "motd", TaterUtils.logger());
        OreWatcherConfig oreWatcher = get(root, oreWatcherType, "oreWatcher", TaterUtils.logger());
        PingConfig ping = get(root, pingType, "ping", TaterUtils.logger());
        SlashLobbyConfig slashLobby = get(root, slashLobbyType, "slashLobby", TaterUtils.logger());
        SpawnConfig spawn = get(root, spawnType, "spawn", TaterUtils.logger());

        switch (version) {
            case 1:
                config =
                        new TaterUtilsConfig_V1(
                                modules,
                                badSpawns,
                                chatFormatter,
                                gameMode,
                                godMode,
                                home,
                                joinAndQuit,
                                motd,
                                oreWatcher,
                                ping,
                                slashLobby,
                                spawn);
                break;
            default:
                TaterUtils.logger().error("Unknown configuration version: " + version);
        }
    }

    /** Unload the configuration. */
    public static void unload() {
        config = null;
    }

    /** Save the configuration to the file. */
    public static void save() {
        if (config == null) {
            return;
        }
        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root = getRoot(loader, TaterUtils.logger());
        if (root == null) {
            return;
        }

        set(root, versionType, "version", config.version(), TaterUtils.logger());
        set(root, moduleType, "modules", config.modules(), TaterUtils.logger());
        set(root, badSpawnsType, "badSpawns", config.badSpawns(), TaterUtils.logger());
        set(root, chatFormatterType, "chatFormatter", config.chatFormatter(), TaterUtils.logger());
        set(root, gameModeType, "gameMode", config.gameMode(), TaterUtils.logger());
        set(root, godModeType, "godMode", config.godMode(), TaterUtils.logger());
        set(root, homeType, "home", config.home(), TaterUtils.logger());
        set(root, joinAndQuitType, "joinAndQuit", config.joinAndQuit(), TaterUtils.logger());
        set(root, motdType, "motd", config.motd(), TaterUtils.logger());
        set(root, oreWatcherType, "oreWatcher", config.oreWatcher(), TaterUtils.logger());
        set(root, pingType, "ping", config.ping(), TaterUtils.logger());
        set(root, slashLobbyType, "slashLobby", config.slashLobby(), TaterUtils.logger());
        set(root, spawnType, "spawn", config.spawn(), TaterUtils.logger());

        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            TaterUtils.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /**
     * Get the loaded configuration.
     *
     * @return The loaded configuration.
     */
    public static TaterUtilsConfig config() {
        if (config == null) {
            load();
        }
        return config;
    }
}
