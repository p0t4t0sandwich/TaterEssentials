/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.slashlobby.command;

import static dev.neuralnexus.taterapi.placeholder.PlaceholderParser.substituteSectionSign;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.ProxyPlayer;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;

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
        player.sendMessage(
                substituteSectionSign(
                        TaterUtilsConfigLoader.config().slashLobby().connectedToLobby()));
        player.connect(TaterUtilsConfigLoader.config().slashLobby().lobbyNames()[0]);
        return true;
    }
}
