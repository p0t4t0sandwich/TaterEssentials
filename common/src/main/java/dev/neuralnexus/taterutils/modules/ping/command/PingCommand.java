package dev.neuralnexus.taterutils.modules.ping.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;

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

        if (args.length == 0) {
            if (sender.isPlayer()) {
                CommandUtils.sendMessage(
                        sender,
                        ((Player) sender)
                                .parsePlaceholders(
                                        TaterUtilsConfig.PingConfig.getMessage("pingSelf"))
                                .parseString("ping", String.valueOf(((Player) sender).ping()))
                                .getResult());
            } else {
                CommandUtils.sendMessage(
                        sender, TaterUtilsConfig.PingConfig.getMessage("specifyPlayer"));
            }
        } else {
            if (sender.hasPermission(permission() + ".others")) {
                Optional<SimplePlayer> target =
                        TaterAPIProvider.get().getServer().onlinePlayers().stream()
                                .filter(player -> player.name().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (target.isPresent()) {
                    CommandUtils.sendMessage(
                            sender,
                            target.get()
                                    .parsePlaceholders(
                                            TaterUtilsConfig.PingConfig.getMessage("pingOther"))
                                    .parseString("ping", String.valueOf(target.get().ping()))
                                    .getResult());
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.PingConfig.getMessage("playerNotFound"));
                }
            } else {
                CommandUtils.sendMessage(
                        sender, TaterUtilsConfig.PingConfig.getMessage("pingOtherDenied"));
            }
        }
        return true;
    }
}
