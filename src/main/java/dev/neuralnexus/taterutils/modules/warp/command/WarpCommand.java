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

/** Warp Command. */
public class WarpCommand implements Command {
    private String name = "warp";

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
        return "Teleports you to a warp!";
    }

    @Override
    public String usage() {
        return "/warp [name]";
    }

    @Override
    public String permission() {
        return "taterutils.command.warp";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        WarpAPI api = TaterUtilsAPIProvider.get().warpAPI();

        if (args.length == 0) {
            CommandUtils.sendMessage(player, "&Available Warps: &e" + api.getWarps().toString());
        } else {
            if (!player.hasPermission("taterutils.command.warp." + args[0])) {
                CommandUtils.sendMessage(
                        player, "&cYou do not have permission to warp to " + args[0] + ".");
                return true;
            }
            if (args[0].equalsIgnoreCase("list")) {
                CommandUtils.sendMessage(
                        player, "&aAvailable Warps: &e" + api.getWarps().toString());
                return true;
            }
            if (!api.teleportWarp(player, args[0])) {
                CommandUtils.sendMessage(player, "&cWarp " + args[0] + " does not exist.");
                return true;
            }
            CommandUtils.sendMessage(player, "&aWarped to &e" + args[0] + "&a.");
        }
        return true;
    }
}
