package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.home.api.HomeAPI;

/** DelHome Command. */
public class DelHomeCommand implements Command {
    private String name = "delhome";

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
        return "Allows players to delete a home.";
    }

    @Override
    public String usage() {
        return "/delhome <name>";
    }

    @Override
    public String permission() {
        return "taterutils.command.delhome";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        HomeAPI api = TaterUtilsAPIProvider.get().homeAPI();

        String message;
        if (args.length == 0) {
            message = TaterUtilsConfig.HomeConfig.getMessage("home.noName");
        } else {
            api.setHome(player, args[0], player.location());
            message =
                    new PlaceholderParser(TaterUtilsConfig.HomeConfig.getMessage("delhome.success"))
                            .parseString("home", args[0])
                            .getResult();
        }
        player.sendMessage(message);
        return true;
    }
}
