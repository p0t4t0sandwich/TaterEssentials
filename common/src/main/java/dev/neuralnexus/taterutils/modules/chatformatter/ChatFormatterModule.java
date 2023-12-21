package dev.neuralnexus.taterutils.modules.chatformatter;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.Module;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsConfig;

/** ChatFormatter module. */
public class ChatFormatterModule implements Module {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String getName() {
        return "ChatFormatter";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has already started!");
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
                                                event.getPlayer()
                                                        .parsePlaceholders(
                                                                TaterUtilsConfig.ChatFormatterConfig
                                                                        .getMessage(
                                                                                "playerMessage"))
                                                        .parseString("message", event.getMessage())
                                                        .getResult()));
                    });
        }

        TaterUtils.getLogger().info("Submodule " + getName() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has already stopped!");
            return;
        }
        STARTED = false;

        // Remove references to objects

        TaterUtils.getLogger().info("Submodule " + getName() + " has been stopped!");
    }

    @Override
    public void reload() {
        if (!STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        TaterUtils.getLogger().info("Submodule " + getName() + " has been reloaded!");
    }
}
