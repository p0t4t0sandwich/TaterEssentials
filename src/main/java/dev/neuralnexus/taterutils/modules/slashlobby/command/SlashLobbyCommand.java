package dev.neuralnexus.taterutils.modules.slashlobby.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterutils.TaterUtilsConfigOld;
import dev.neuralnexus.taterutils.api.CommandUtils;

/** SlashLobby Command. */
public class SlashLobbyCommand implements Command {
    private String name = "slashlobby";

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
        return "Command that players can use to send themselves to the lobby.";
    }

    @Override
    public String usage() {
        return "/slashlobby";
    }

    @Override
    public String permission() {
        return "taterutils.command.slashlobby";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }
        ProxyPlayer player = (ProxyPlayer) sender;
        player.connect(TaterUtilsConfigOld.SlashLobbyConfig.getLobbyNames()[0]);
        return true;
    }
}
