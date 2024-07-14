/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.joinandquit;

import static dev.neuralnexus.taterapi.util.TextUtil.substituteSectionSign;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;

/** JoinAndQuit module. */
public class JoinAndQuitModule implements PluginModule {
    @Override
    public String id() {
        return "JoinAndQuit";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register listeners
            PlayerEvents.LOGIN.register(
                    event ->
                            event.setLoginMessage(
                                    substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigLoader.config()
                                                                    .joinAndQuit()
                                                                    .join())
                                                    .getResult())));
            PlayerEvents.LOGOUT.register(
                    event ->
                            event.setLogoutMessage(
                                    substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigLoader.config()
                                                                    .joinAndQuit()
                                                                    .quit())
                                                    .getResult())));
        }
    }
}
