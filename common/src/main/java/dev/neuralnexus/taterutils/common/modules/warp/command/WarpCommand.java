package dev.neuralnexus.taterutils.common.modules.warp.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.warp.api.WarpAPI;

/** Warp Command. */
public class WarpCommand implements Command {
    private String name = "warp";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
