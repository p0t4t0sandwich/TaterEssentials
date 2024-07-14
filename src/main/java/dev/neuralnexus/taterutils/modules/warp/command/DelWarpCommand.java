/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.warp.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.warp.api.WarpAPI;

/** SetWarp Command. */
public class DelWarpCommand implements Command {
    private String name = "delwarp";

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
        return "Deletes a warp location!";
    }

    @Override
    public String usage() {
        return "/delwarp <name>";
    }

    @Override
    public String permission() {
        return "taterutils.command.delwarp";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        WarpAPI api = TaterUtilsAPIProvider.get().warpAPI();

        if (args.length == 0) {
            CommandUtils.sendMessage(player, "&aPlease provide a Warp name!");
        } else {
            api.deleteWarp(args[0]);
            CommandUtils.sendMessage(player, "&aDeleted warp &e" + args[0] + "&a.");
        }
        return true;
    }
}
