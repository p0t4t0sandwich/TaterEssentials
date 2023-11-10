package dev.neuralnexus.taterutils.common.commands.warp;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.warp.WarpAPI;
import dev.neuralnexus.taterutils.common.commands.CommandUtils;

/**
 * SetWarp Command.
 */
public class SetWarpCommand implements Command {
    private String name = "setwarp";

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
        return "Sets a warp location!";
    }

    @Override
    public String getUsage() {
        return "/setwarp <name>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.setwarp";
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
            player.sendMessage(Utils.substituteSectionSign("&aPlease Provide a Warp Name!"));
        } else {
            if (api.getInvalidWarpNames().contains(args[0])) {
                player.sendMessage(Utils.substituteSectionSign("&cInvalid warp name."));
                return true;
            }
            api.setWarp(args[0], player.getLocation());
            player.sendMessage(Utils.substituteSectionSign("&aSet warp &e" + args[0] + "&a."));
        }
        return true;
    }
}
