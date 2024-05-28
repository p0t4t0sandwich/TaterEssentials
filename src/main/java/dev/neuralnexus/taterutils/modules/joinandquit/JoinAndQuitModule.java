/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.joinandquit;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;

/** JoinAndQuit module. */
public class JoinAndQuitModule implements PluginModule {
    private static boolean RELOADED = false;
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "JoinAndQuit";
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
            PlayerEvents.LOGIN.register(
                    event ->
                            event.setLoginMessage(
                                    Utils.substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigLoader.config()
                                                                    .joinAndQuit()
                                                                    .join())
                                                    .getResult())));
            PlayerEvents.LOGOUT.register(
                    event ->
                            event.setLogoutMessage(
                                    Utils.substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigLoader.config()
                                                                    .joinAndQuit()
                                                                    .quit())
                                                    .getResult())));
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
        RELOADED = true;
        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
