/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.ping.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.PingConfig;

import java.util.Optional;

/** Ping Command. */
public class PingCommand implements Command {
    private String name = "ping";

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
        return "Gets the ping of a player";
    }

    @Override
    public String usage() {
        return "/ping [player]";
    }

    @Override
    public String permission() {
        return "taterutils.command.ping";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }

        PingConfig config = TaterUtilsConfigLoader.config().ping();
        if (args.length == 0) {
            if (sender.isPlayer()) {
                CommandUtils.sendMessage(
                        sender,
                        ((Player) sender)
                                .parsePlaceholders(config.pingSelf())
                                .parseString("ping", String.valueOf(((Player) sender).ping()))
                                .getResult());
            } else {
                CommandUtils.sendMessage(sender, config.specifyPlayer());
            }
        } else {
            if (sender.hasPermission(permission() + ".others")) {
                Optional<SimplePlayer> target =
                        TaterAPIProvider.api().get().server().onlinePlayers().stream()
                                .filter(player -> player.name().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (target.isPresent()) {
                    CommandUtils.sendMessage(
                            sender,
                            target.get()
                                    .parsePlaceholders(config.pingOther())
                                    .parseString("ping", String.valueOf(target.get().ping()))
                                    .getResult());
                } else {
                    CommandUtils.sendMessage(sender, config.playerNotFound());
                }
            } else {
                CommandUtils.sendMessage(sender, config.pingOtherDenied());
            }
        }
        return true;
    }
}
