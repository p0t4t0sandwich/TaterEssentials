/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.spawn.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;

/** Spawn Command. */
public class SpawnCommand implements Command {
    private String name = "spawn";

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String description() {
        return "Teleports you to spawn.";
    }

    @Override
    public String usage() {
        return "/spawn";
    }

    @Override
    public String permission() {
        return "taterutils.command.spawn";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        SpawnAPI api = TaterUtilsAPIProvider.get().spawnAPI();

        String message;
        if (!api.teleportSpawn(player)) {
            message = TaterUtilsConfigLoader.config().spawn().spawnNotSet();
        } else {
            message = TaterUtilsConfigLoader.config().spawn().teleportedToSpawn();
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
