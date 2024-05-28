/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/Switchboard/blob/dev/LICENSE">GPL-3</a> The API is
 * Licensed under <a
 * href="https://github.com/p0t4t0sandwich/Switchboard/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterutils.config;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.config.sections.BadSpawnsConfig;
import dev.neuralnexus.taterutils.config.sections.ChatFormatterConfig;
import dev.neuralnexus.taterutils.config.sections.GameModeConfig;
import dev.neuralnexus.taterutils.config.versions.TaterUtilsConfig_V1;

import io.leangen.geantyref.TypeToken;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/** A class for loading Switchboard configuration. */
public class TaterUtilsConfigLoader {
    private static final Path configPath =
            Paths.get(
                    TaterAPIProvider.serverType().dataFolders().configFolder()
                            + File.separator
                            + TaterUtils.PROJECT_NAME
                            + File.separator
                            + TaterUtils.PROJECT_ID
                            + ".conf");
    private static final String defaultConfigPath = "source." + TaterUtils.PROJECT_ID + ".conf";
    private static final TypeToken<List<ModuleConfig>> moduleType =
            new TypeToken<List<ModuleConfig>>() {};
    private static final TypeToken<BadSpawnsConfig> badSpawnsType =
            new TypeToken<BadSpawnsConfig>() {};
    private static final TypeToken<ChatFormatterConfig> chatFormatterType =
            new TypeToken<ChatFormatterConfig>() {};
    private static final TypeToken<GameModeConfig> gameModeType =
            new TypeToken<GameModeConfig>() {};
    private static TaterUtilsConfig config;

    /** Copy the default configuration to the config folder. */
    public static void copyDefaults() {
        if (configPath.toFile().exists()) {
            return;
        }
        try {
            Files.createDirectories(configPath.getParent());
            Files.copy(
                    Objects.requireNonNull(
                            TaterUtilsConfigLoader.class
                                    .getClassLoader()
                                    .getResourceAsStream(defaultConfigPath)),
                    configPath);
        } catch (IOException e) {
            TaterUtils.logger()
                    .error(
                            "An error occurred while copying the default configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    }

    /** Load the configuration from the file. */
    public static void load() {
        copyDefaults();

        final HoconConfigurationLoader loader =
                HoconConfigurationLoader.builder().path(configPath).build();
        CommentedConfigurationNode root;
        try {
            root = loader.load();
        } catch (ConfigurateException e) {
            TaterUtils.logger()
                    .error("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return;
        }

        ConfigurationNode versionNode = root.node("version");
        int version = versionNode.getInt(1);

        List<ModuleConfig> modules = null;
        try {
            modules = root.node("modules").get(moduleType);
        } catch (SerializationException e) {
            TaterUtils.logger()
                    .error(
                            "An error occurred while loading the modules configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        BadSpawnsConfig badSpawns = null;
        try {
            badSpawns = root.node("badSpawns").get(badSpawnsType);
        } catch (SerializationException e) {
            TaterUtils.logger()
                    .error(
                            "An error occurred while loading the discord configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        ChatFormatterConfig chatFormatter = null;
        try {
            chatFormatter = root.node("chatFormatter").get(chatFormatterType);
        } catch (SerializationException e) {
            TaterUtils.logger()
                    .error(
                            "An error occurred while loading the chat configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        GameModeConfig gameMode = null;
        try {
            gameMode = root.node("gameMode").get(gameModeType);
        } catch (SerializationException e) {
            TaterUtils.logger()
                    .error(
                            "An error occurred while loading the gameMode configuration: "
                                    + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        switch (version) {
            case 1:
                config = new TaterUtilsConfig_V1(modules, badSpawns, chatFormatter, gameMode);
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
        CommentedConfigurationNode root;
        try {
            root = loader.load();
        } catch (ConfigurateException e) {
            TaterLib.logger()
                    .error("An error occurred while loading this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            return;
        }

        try {
            root.node("version").set(config.version());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("modules").set(moduleType, config.modules());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("badSpawns").set(badSpawnsType, config.badSpawns());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("chatFormatter").set(chatFormatterType, config.chatFormatter());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            root.node("gameMode").set(gameModeType, config.gameMode());
        } catch (SerializationException e) {
            TaterLib.logger()
                    .error("An error occurred while saving this configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }

        try {
            loader.save(root);
        } catch (ConfigurateException e) {
            TaterLib.logger()
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
