package dev.neuralnexus.taterutils.modules.send.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.server.ProxyServer;
import dev.neuralnexus.taterutils.api.CommandUtils;

import java.util.Optional;

/** TpAccept Command. */
public class SendCommand implements Command {
    private String name = "send";

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
        return "Allows you to send a player to another server.";
    }

    @Override
    public String usage() {
        return "/send <player> <server>";
    }

    @Override
    public String permission() {
        return "taterutils.command.send";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }

        ProxyServer server = (ProxyServer) TaterAPIProvider.get().getServer();

        Optional<ProxyPlayer> player =
                server.onlinePlayers().stream()
                        .filter(p -> p.name().equalsIgnoreCase(args[0]))
                        .findFirst()
                        .map(p -> (ProxyPlayer) p);

        if (!player.isPresent()) {
            sender.sendMessage("Player not found!");
            return true;
        }

        if (server.servers().stream().noneMatch(s -> s.name().equalsIgnoreCase(args[1]))) {
            sender.sendMessage("Server not found!");
            return true;
        }

        sender.sendMessage("Sending " + args[0] + " to " + args[1] + "...");
        player.get().connect(args[1]);
        return true;
    }
}
