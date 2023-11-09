package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPI;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;

import java.util.Arrays;

/**
 * Home Command.
 */
public class HomeCommand implements Command {
    private String name = "home";

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
        return "Allows players to set and teleport to their home.";
    }

    @Override
    public String getUsage() {
        return "/home [set] [name]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.home";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PlaceholderParser.substituteSectionSign("&cOnly players can use this command."));
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission(getPermission())) {
            player.sendMessage(PlaceholderParser.substituteSectionSign("&cYou do not have permission to use this command."));
            return true;
        }
        HomeAPI api = TaterUtilsAPIProvider.get().getHomeAPI();

        if (args.length == 0) {
            if (!api.teleportHome(player, "home")) {
                player.sendMessage(PlaceholderParser.substituteSectionSign("&cYou do not have a home set."));
                return true;
            }
            player.sendMessage(PlaceholderParser.substituteSectionSign("&aTeleported to your home."));
            return true;
        } else {
            switch (args[0]) {
                case "set":
                    if (args.length == 1) {
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&cInvalid arguments."));
                        return true;
                    }
                    api.setHome(player, args[1], player.getLocation());
                    player.sendMessage(PlaceholderParser.substituteSectionSign("&aSet your home."));
                    return true;
                case "delete":
                    if (args.length == 1) {
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&cInvalid arguments."));
                        return true;
                    }
                    api.deleteHome(player, args[1]);
                    player.sendMessage(PlaceholderParser.substituteSectionSign("&aDeleted your home."));
                    return true;
                default:
                    if (!api.teleportHome(player, args[0])) {
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&cYou do not have home &e" + args[0] + " &cset."));
                        return true;
                    }
                    player.sendMessage(PlaceholderParser.substituteSectionSign("&aTeleported to home &e" + args[0] + "&a."));
                    return true;
            }
        }
    }
}
