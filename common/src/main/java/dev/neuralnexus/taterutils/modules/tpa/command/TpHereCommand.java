package dev.neuralnexus.taterutils.modules.tpa.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.tpa.api.TpaAPI;

import java.util.Optional;

/** TpHere Command. */
public class TpHereCommand implements Command {
    private String name = "tphere";

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
        return "Allows players to request another player to teleport to them!";
    }

    @Override
    public String usage() {
        return "/tphere <player>";
    }

    @Override
    public String permission() {
        return "taterutils.command.tphere";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        TpaAPI api = TaterUtilsAPIProvider.get().tpaAPI();

        if (args.length == 0) {
            CommandUtils.sendMessage(player, "&aPlease provide a player name!");
        } else {
            Optional<Player> target =
                    TaterAPIProvider.get().getServer().onlinePlayers().stream()
                            .filter(p -> p.name().equalsIgnoreCase(args[0]))
                            .findFirst()
                            .map(p -> (Player) p);
            if (target.isPresent() && !api.hasPendingRequest(player)) {
                api.addRequest(player, target.get());
                CommandUtils.sendMessage(
                        player,
                        "&aYou have requested &e" + target.get().name() + " &ato teleport to you!");
            } else {
                CommandUtils.sendMessage(
                        player,
                        "&cThat player is not online or you already have an active request!");
            }
        }
        return true;
    }
}
