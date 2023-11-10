package dev.neuralnexus.taterutils.common.commands.warp;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.warp.WarpAPI;
import dev.neuralnexus.taterutils.common.commands.CommandUtils;

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
        return "Teleports you to a warp!";
    }

    @Override
    public String getUsage() {
        return "/warp [name]";
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
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, getPermission())) {
            return true;
        }
        Player player = (Player) sender;
        WarpAPI api = TaterUtilsAPIProvider.get().getWarpAPI();

        if (args.length == 0) {
            player.sendMessage(Utils.substituteSectionSign("&aAvailable Warps: &e" + api.getWarps().toString()));
        } else {
            if (!CommandUtils.playerHasPermission(player, "taterutils.command.warp." + args[0])) {
                player.sendMessage(Utils.substituteSectionSign("&cYou do not have permission to warp to " + args[0] + "."));
                return true;
            }
            if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage(Utils.substituteSectionSign("&aAvailable Warps: &e" + api.getWarps().toString()));
                return true;
            }
            if (!api.teleportWarp(player, args[0])) {
                player.sendMessage(Utils.substituteSectionSign("&cWarp " + args[0] + " does not exist."));
                return true;
            }
            player.sendMessage(Utils.substituteSectionSign("&aTeleported to warp location: &e" + args[0] + "&a."));
        }
        return true;
    }
}
