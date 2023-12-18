package dev.neuralnexus.taterutils.modules.ping.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
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
                sender.sendMessage("Your ping is " + ((Player) sender).getPing() + "ms");
            } else {
                sender.sendMessage("You must specify a player!");
            }
        } else {
            if (sender.hasPermission("taterutils.command.ping.others")) {
                Optional<Player> target =
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(player -> player.getName().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (target.isPresent()) {
                    sender.sendMessage(
                            target.get().getName() + "'s ping is " + target.get().getPing() + "ms");
                } else {
                    sender.sendMessage("Player not found!");
                }
            } else {
                sender.sendMessage("You do not have permission to view other players' ping!");
            }
        }
        return true;
    }
}
