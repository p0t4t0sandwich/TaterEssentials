package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;
import dev.neuralnexus.taterutils.common.api.modules.WarpAPI;

/**
 * Warp Command.
 */
public class WarpCommand implements Command {
    private String name = "warp";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Allows players to Send a teleport request to another player!";
    }

    @Override
    public String getUsage() {
        return "/warp [list/location name]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.warp";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        /**
         * CONFIRMING SENDER IS PLAYER
         */
        if (!(sender instanceof Player)) {
            sender.sendMessage(PlaceholderParser.substituteSectionSign("&cOnly players can use this command."));
            return true;
        }
        Player player = (Player) sender;
        /**
         * CONFIRMING SENDER HAS REQUIRED PERMS
         */
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(PlaceholderParser.substituteSectionSign("&cYou do not have permission to use this command."));
            return true;
        }
        /**
         * SET THE WARP BOISSS
         */
        WarpAPI api = TaterUtilsAPIProvider.get().getWarpAPI();

        if (args.length == 0) {
            //FIXME MAKE IT SO IF NO ARG IS PROVIDED IT GIVES A LIST OF WARPS
            player.sendMessage(PlaceholderParser.substituteSectionSign("&aPlease Provide a Warp Name!"));
            return true;
        } else {
            if (!api.teleportWarp(player, args[0])) {
                player.sendMessage(PlaceholderParser.substituteSectionSign("&cNo Warp by the name: &e" + args[0] ));
                return true;
            }
            player.sendMessage(PlaceholderParser.substituteSectionSign("&aTeleported to warp location: &e" + args[0] + "&a."));
            return true;
        }
    }
}

