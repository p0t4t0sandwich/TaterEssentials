package dev.neuralnexus.taterutils.common.modules.tpa.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.tpa.api.TpaAPI;

/** TpAccept Command. */
public class TpAcceptCommand implements Command {
    private String name = "tpaccept";

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
        return "Allows players to accept a teleport request!";
    }

    @Override
    public String getUsage() {
        return "/tpaccept";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.tpaccept";
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
        TpaAPI api = TaterUtilsAPIProvider.get().getTpaAPI();

        if (api.hasPendingRequest(player)) {
            api.acceptRequest(player);
        } else {
            CommandUtils.sendMessage(player, "&cYou do not have any pending requests.");
        }
        return true;
    }
}
