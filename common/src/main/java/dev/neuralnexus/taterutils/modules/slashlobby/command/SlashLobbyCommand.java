package dev.neuralnexus.taterutils.modules.slashlobby.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.send.api.SendAPI;

/** SlashLobby Command. */
public class SlashLobbyCommand implements Command {
    private String name = "slashlobby";

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
        return "Command that players can use to send themselves to the lobby.";
    }

    @Override
    public String getUsage() {
        return "/slashlobby";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.slashlobby";
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

        api.sendPlayer(player, TaterUtilsConfig.SlashLobbyConfig.getLobbyNames()[0]);
        return true;
    }
}
