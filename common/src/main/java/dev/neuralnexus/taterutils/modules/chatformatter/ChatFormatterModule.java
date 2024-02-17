package dev.neuralnexus.taterutils.modules.chatformatter;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsConfig;

/** ChatFormatter module. */
public class ChatFormatterModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "ChatFormatter";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register listeners
            PlayerEvents.MESSAGE.register(
                    event -> {
                        event.setCancelled(true);
                        TaterAPIProvider.get()
                                .getServer()
                                .broadcastMessage(
                                        Utils.substituteSectionSign(
                                                event.player()
                                                        .parsePlaceholders(
                                                                TaterUtilsConfig.ChatFormatterConfig
                                                                        .getMessage(
                                                                                "playerMessage"))
                                                        .parseString("message", event.message())
                                                        .getResult()));
                    });
        }

        TaterUtils.logger().info("Submodule " + name() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects

        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }

    @Override
    public void reload() {
        if (!STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        TaterUtils.logger().info("Submodule " + name() + " has been reloaded!");
    }
}
