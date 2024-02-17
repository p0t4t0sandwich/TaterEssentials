package dev.neuralnexus.taterutils.modules.warp.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.warp.api.WarpAPI;

/** SetWarp Command. */
public class SetWarpCommand implements Command {
    private String name = "setwarp";

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
        return "Sets a warp location!";
    }

    @Override
    public String usage() {
        return "/setwarp <name>";
    }

    @Override
    public String permission() {
        return "taterutils.command.setwarp";
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
            if (api.getInvalidWarpNames().contains(args[0])) {
                CommandUtils.sendMessage(player, "&cInvalid warp name.");
                return true;
            }
            api.setWarp(args[0], player.location());
            CommandUtils.sendMessage(player, "&aSet warp &e" + args[0] + "&a.");
        }
        return true;
    }
}
