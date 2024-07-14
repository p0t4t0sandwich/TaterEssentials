/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.motd;

import static dev.neuralnexus.taterapi.placeholder.PlaceholderParser.substituteSectionSign;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;

/** Motd module. */
public class MotdModule implements PluginModule {
    @Override
    public String id() {
        return "Motd";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register listeners
            PlayerEvents.LOGIN.register(
                    event ->
                            event.player()
                                    .sendMessage(
                                            substituteSectionSign(
                                                    TaterUtilsConfigLoader.config()
                                                            .motd()
                                                            .message())));
        }
    }
}
