package dev.neuralnexus.taterutils.common.modules.send.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.send.api.SendAPI;

import java.util.Optional;

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

        ProxyServer server = (ProxyServer) TaterAPIProvider.get().getServer();

        Optional<Player> player =
                server.getOnlinePlayers().stream()
                        .filter(p -> p.getName().equalsIgnoreCase(args[0]))
                        .findFirst();
        SendAPI api = TaterUtilsAPIProvider.get().getSendAPI();

        if (!player.isPresent()) {
            sender.sendMessage("Player not found!");
            return true;
        }

        if (server.getServers().stream().noneMatch(s -> s.getName().equalsIgnoreCase(args[1]))) {
            sender.sendMessage("Server not found!");
            return true;
        }

        sender.sendMessage("Sending " + args[0] + " to " + args[1] + "...");
        api.sendPlayer(player.get(), args[1]);
        return true;
    }
}
