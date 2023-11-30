package dev.neuralnexus.taterutils;

import dev.neuralnexus.taterlib.lib.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** Config handler. */
public class TaterUtilsConfig {
    private static YamlDocument config;

    /**
     * Load the config
     *
     * @param configFolder The path to the config file
     */
    public static void loadConfig(String configFolder) {
        try {
            config =
                    YamlDocument.create(
                            new File(
                                    "."
                                            + File.separator
                                            + configFolder
                                            + File.separator
                                            + TaterUtils.Constants.PROJECT_NAME,
                                    TaterUtils.Constants.PROJECT_ID + ".config.yml"),
                            Objects.requireNonNull(
                                    TaterUtils.class
                                            .getClassLoader()
                                            .getResourceAsStream(
                                                    TaterUtils.Constants.PROJECT_ID
                                                            + ".config.yml")));
            config.reload();
        } catch (IOException | NullPointerException e) {
            TaterUtils.getLogger()
                    .info(
                            "Failed to load "
                                    + TaterUtils.Constants.PROJECT_ID
                                    + ".config.yml!\n"
                                    + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Unload the config */
    public static void unloadConfig() {
        config = null;
    }

    /** Save the config */
    public static void saveConfig() {
        try {
            config.save();
        } catch (IOException e) {
            TaterUtils.getLogger()
                    .info(
                            "Failed to save "
                                    + TaterUtils.Constants.PROJECT_ID
                                    + ".config.ymll!\n"
                                    + e.getMessage());
        }
    }

    /**
     * Check to see if a module is enabled
     *
     * @param module The module
     */
    public static boolean isModuleEnabled(String module) {
        return config.getBoolean("modules" + module + ".enabled");
    }

    /** BadSpawns config. */
    public static class BadSpawnsConfig {
        /**
         * Get banned mobs.
         *
         * @return The banned mobs.
         */
        public static List<String> getBannedMobs() {
            return config.getStringList("modules.badspawns.bannedMobs");
        }

        /**
         * Get regions.
         *
         * @return The regions.
         */
        public static List<Map<?, ?>> getRegions() {
            return config.getMapList("modules.badspawns.regions");
        }
    }

    /** Home config. */
    public static class HomeConfig {
        /**
         * Get a message from the config.
         *
         * @param path The path to the message.
         */
        public static String getMessage(String path) {
            return config.getString("modules.home.messages." + path);
        }
    }

    /** Motd config. */
    public static class MotdConfig {
        /**
         * Get a message from the config.
         *
         * @param path The path to the message.
         */
        public static String getMessage(String path) {
            return config.getString("modules.motd.messages." + path);
        }
    }

    /** OreWatcher config. */
    public static class OreWatcherConfig {
        /**
         * Get a message from the config.
         *
         * @param path The path to the message.
         */
        public static String getMessage(String path) {
            return config.getString("modules.oreWatcher.messages." + path);
        }

        /**
         * Get the alert threshold.
         *
         * @return The alert threshold.
         */
        public static int getAlertThreshold() {
            return config.getInt("modules.oreWatcher.alertThreshold");
        }

        /**
         * Get whether to cancel the event if the threshold is met.
         *
         * @return Whether to cancel the event if the threshold is met.
         */
        public static boolean getCancelMinedOverThreshold() {
            return config.getBoolean("modules.oreWatcher.cancelMinedOverThreshold");
        }

        /**
         * Get whether to send an alert to admins.
         *
         * @return Whether to send an alert to admins.
         */
        public static boolean getAdminAlertEnabled() {
            return config.getBoolean("modules.oreWatcher.adminAlertEnabled");
        }

        /**
         * Get whether to send an alert to the player.
         *
         * @return Whether to send an alert to the player.
         */
        public static boolean getPlayerAlertEnabled() {
            return config.getBoolean("modules.oreWatcher.playerAlertEnabled");
        }
    }

    /** SlashLobby config. */
    public static class SlashLobbyConfig {
        /**
         * Get a message from the config.
         *
         * @param path The path to the message.
         */
        public static String getMessage(String path) {
            return config.getString("modules.slashLobby.messages." + path);
        }

        /**
         * Get the lobby names.
         *
         * @return The lobby names.
         */
        public static String[] getLobbyNames() {
            return config.getStringList("modules.slashLobby.lobbyNames").toArray(new String[0]);
        }
    }

    /** Spawn config. */
    public static class SpawnConfig {
        /**
         * Get a message from the config.
         *
         * @param path The path to the message.
         */
        public static String getMessage(String path) {
            return config.getString("modules.spawn.messages." + path);
        }
    }
}
