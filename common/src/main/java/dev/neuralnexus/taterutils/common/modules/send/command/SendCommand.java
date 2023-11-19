package dev.neuralnexus.taterutils.common.modules.send.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.send.api.SendAPI;

/** TpAccept Command. */
public class SendCommand implements Command {
    private String name = "send";

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
        return "Allows you to send a player to another server.";
    }

    @Override
    public String getUsage() {
        return "/send <player> <server>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.send";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, getPermission())) {
            return true;
        }
        Player player = (Player) sender;
        SendAPI api = TaterUtilsAPIProvider.get().getSendAPI();

        api.sendPlayer(player, args[0]);
        return true;
    }
}
