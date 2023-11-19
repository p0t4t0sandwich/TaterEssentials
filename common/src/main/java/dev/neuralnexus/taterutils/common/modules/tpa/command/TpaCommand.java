package dev.neuralnexus.taterutils.common.modules.tpa.command;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.tpa.api.TpaAPI;

/** Tpa Command. */
public class TpaCommand implements Command {
    private String name = "tpa";

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
        return "Allows players to Send a teleport request to another player!";
    }

    @Override
    public String getUsage() {
        return "/tpa <player>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.tpa";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, getPermission())) {
            return true;
        }
        Player player = (Player) sender;
        TpaAPI api = TaterUtilsAPIProvider.get().getTpaAPI();

        if (args.length == 0) {
            CommandUtils.sendMessage(player, "&aPlease provide a player name!");
        } else {
            Player target =
                    TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                            .filter(p -> p.getName().equalsIgnoreCase(args[0]))
                            .findFirst()
                            .orElse(null);
            if (target != null && !api.hasPendingRequest(target)) {
                api.addRequest(player, target);
                CommandUtils.sendMessage(
                        player,
                        "&aYou have sent a teleport request to &e" + target.getName() + "&a!");
            } else {
                CommandUtils.sendMessage(
                        player, "&cThat player is not online or already has a request!");
            }
        }
        return true;
    }
}
