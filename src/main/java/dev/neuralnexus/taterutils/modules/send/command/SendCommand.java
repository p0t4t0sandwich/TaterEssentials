/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.send.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterapi.server.ProxyServer;
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

        ProxyServer server = (ProxyServer) TaterAPIProvider.api().get().server();

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
