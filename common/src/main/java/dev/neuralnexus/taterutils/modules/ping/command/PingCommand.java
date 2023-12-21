package dev.neuralnexus.taterutils.modules.ping.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;

import java.util.Optional;

/** Ping Command. */
public class PingCommand implements Command {
    private String name = "ping";

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
        return "Gets the ping of a player";
    }

    @Override
    public String getUsage() {
        return "/ping [player]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.ping";
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

        if (args.length == 0) {
            if (sender.isPlayer()) {
                CommandUtils.sendMessage(
                        sender,
                        ((Player) sender)
                                .parsePlaceholders(
                                        TaterUtilsConfig.PingConfig.getMessage("pingSelf"))
                                .parseString("ping", String.valueOf(((Player) sender).getPing()))
                                .getResult());
            } else {
                CommandUtils.sendMessage(
                        sender, TaterUtilsConfig.PingConfig.getMessage("specifyPlayer"));
            }
        } else {
            if (sender.hasPermission(getPermission() + ".others")) {
                Optional<Player> target =
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(player -> player.getName().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (target.isPresent()) {
                    CommandUtils.sendMessage(
                            sender,
                            target.get()
                                    .parsePlaceholders(
                                            TaterUtilsConfig.PingConfig.getMessage("pingOther"))
                                    .parseString("ping", String.valueOf(target.get().getPing()))
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
