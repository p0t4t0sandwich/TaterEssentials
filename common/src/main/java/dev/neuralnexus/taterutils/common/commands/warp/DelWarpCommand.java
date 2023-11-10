package dev.neuralnexus.taterutils.common.commands.warp;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.warp.WarpAPI;
import dev.neuralnexus.taterutils.common.commands.CommandUtils;

/**
 * SetWarp Command.
 */
public class DelWarpCommand implements Command {
    private String name = "delwarp";

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
        return "Deletes a warp location!";
    }

    @Override
    public String getUsage() {
        return "/delwarp <name>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.delwarp";
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
            CommandUtils.sendMessage(player, "&aPlease provide a Warp name!");
        } else {
            api.deleteWarp(args[0]);
            CommandUtils.sendMessage(player, "&aDeleted warp &e" + args[0] + "&a.");
        }
        return true;
    }
}
