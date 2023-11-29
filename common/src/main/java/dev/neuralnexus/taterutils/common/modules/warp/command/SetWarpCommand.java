package dev.neuralnexus.taterutils.common.modules.warp.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.warp.api.WarpAPI;

/** SetWarp Command. */
public class SetWarpCommand implements Command {
    private String name = "setwarp";

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
            CommandUtils.sendMessage(player, "&aPlease provide a Warp name!");
        } else {
            if (api.getInvalidWarpNames().contains(args[0])) {
                CommandUtils.sendMessage(player, "&cInvalid warp name.");
                return true;
            }
            api.setWarp(args[0], player.getLocation());
            CommandUtils.sendMessage(player, "&aSet warp &e" + args[0] + "&a.");
        }
        return true;
    }
}
