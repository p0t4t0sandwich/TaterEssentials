package dev.neuralnexus.taterutils.modules.tpa.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.tpa.api.TpaAPI;

/** TpAccept Command. */
public class TpAcceptCommand implements Command {
    private String name = "tpaccept";

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
        return "Allows players to accept a teleport request!";
    }

    @Override
    public String usage() {
        return "/tpaccept";
    }

    @Override
    public String permission() {
        return "taterutils.command.tpaccept";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        TpaAPI api = TaterUtilsAPIProvider.get().tpaAPI();

        if (api.hasPendingRequest(player)) {
            api.acceptRequest(player);
        } else {
            CommandUtils.sendMessage(player, "&cYou do not have any pending requests.");
        }
        return true;
    }
}
