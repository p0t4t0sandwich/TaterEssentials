package dev.neuralnexus.taterutils.modules.joinandquit;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.Module;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsConfig;

/** JoinAndQuit module. */
public class JoinAndQuitModule implements Module {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String getName() {
        return "JoinAndQuit";
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
            PlayerEvents.LOGIN.register(
                    event ->
                            event.setLoginMessage(
                                    Utils.substituteSectionSign(
                                            event.getPlayer()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfig.MotdConfig.getMessage(
                                                                    "join"))
                                                    .getResult())));
            PlayerEvents.LOGOUT.register(
                    event ->
                            event.setLogoutMessage(
                                    Utils.substituteSectionSign(
                                            event.getPlayer()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfig.MotdConfig.getMessage(
                                                                    "quit"))
                                                    .getResult())));
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
