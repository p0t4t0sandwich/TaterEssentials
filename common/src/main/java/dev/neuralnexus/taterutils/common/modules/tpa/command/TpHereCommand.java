package dev.neuralnexus.taterutils.common.modules.tpa.command;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.tpa.api.TpaAPI;

/**
 * TpHere Command.
 */
public class TpHereCommand implements Command {
    private String name = "tphere";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Allows players to request another player to teleport to them!";
    }

    @Override
    public String getUsage() {
        return "/tphere <player>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.tphere";
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
            Player target = TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
            if (target != null && !api.hasPendingRequest(player)) {
                api.addRequest(player, target);
                CommandUtils.sendMessage(player, "&aYou have requested &e" + target.getName() + " &ato teleport to you!");
            } else {
                CommandUtils.sendMessage(player, "&cThat player is not online or you already have an active request!");
            }
        }
        return true;
    }
}
