/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.gamemode;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterloader.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.gamemode.command.GameModeCommand;

/** Ping module. */
public class GameModeModule implements PluginModule {
    @Override
    public String id() {
        return "GameMode";
    }

    @Override
    public void onEnable() {
        if (!TaterUtils.hasReloaded()) {
            // Register commands
            if (!TaterAPIProvider.platform().isProxy()) {
                CommandEvents.REGISTER_COMMAND.register(
                        event -> {
                            // TODO: Refactor to use aliases once Brig aliases are patched upstream
                            // event.registerCommand(command, "gms", "gmc", "gma", "gmsp");
                            Command gamemode = new GameModeCommand();
                            Command gms = new GameModeCommand();
                            gms.setName("gms");
                            Command gmc = new GameModeCommand();
                            gmc.setName("gmc");
                            Command gma = new GameModeCommand();
                            gma.setName("gma");
                            Command gmsp = new GameModeCommand();
                            gmsp.setName("gmsp");

                            event.registerCommand(gamemode, "gamemode");
                            event.registerCommand(gms);
                            event.registerCommand(gmc);
                            event.registerCommand(gma);
                            event.registerCommand(gmsp);
                        });
            }
        }
    }
}
