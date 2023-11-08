package dev.neuralnexus.taterutils.common;

import dev.neuralnexus.taterlib.lib.dejvokep.boostedyaml.YamlDocument;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Config handler.
 */
public class TaterUtilsConfig {
    private static YamlDocument config;

    /**
     * Load the config
     * @param configFolder The path to the config file
     */
    public static void loadConfig(String configFolder) {
        try {
            config = YamlDocument.create(new File("." + File.separator + configFolder + File.separator + TaterUtils.Constants.PROJECT_NAME, TaterUtils.Constants.PROJECT_ID + ".config.yml"),
                    Objects.requireNonNull(TaterUtils.class.getClassLoader().getResourceAsStream(TaterUtils.Constants.PROJECT_ID + ".config.yml"))
            );
            config.reload();
        } catch (IOException | NullPointerException e) {
            TaterUtils.getLogger().info("Failed to load " + TaterUtils.Constants.PROJECT_ID + ".config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Unload the config
     */
    public static void unloadConfig() {
        config = null;
    }

    /**
     * Save the config
     */
    public static void saveConfig() {
        try {
            config.save();
        } catch (IOException e) {
            TaterUtils.getLogger().info("Failed to save " + TaterUtils.Constants.PROJECT_ID + ".config.ymll!\n" + e.getMessage());
        }
    }
}
