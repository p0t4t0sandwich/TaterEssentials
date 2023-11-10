package dev.neuralnexus.taterutils.common.commands.home;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.home.HomeAPI;
import dev.neuralnexus.taterutils.common.commands.CommandUtils;

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
        return "/home [name | del | set | list] [name]";
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
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, getPermission())) {
            return true;
        }
        Player player = (Player) sender;
        HomeAPI api = TaterUtilsAPIProvider.get().getHomeAPI();

        if (args.length == 0) {
            if (!api.teleportHome(player, "home")) {
                player.sendMessage(Utils.substituteSectionSign("&cYou do not have a home set."));
                return true;
            }
            player.sendMessage(Utils.substituteSectionSign("&aTeleported to your home."));
            return true;
        } else {
            switch (args[0]) {
                case "add":
                case "set":
                    if (!CommandUtils.playerHasPermission(player, "taterutils.command.sethome")) {
                        return true;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Utils.substituteSectionSign("&cInvalid arguments."));
                        return true;
                    }
                    if (api.getInvalidHomeNames().contains(args[1])) {
                        player.sendMessage(Utils.substituteSectionSign("&cInvalid home name."));
                        return true;
                    }
                    api.setHome(player, args[1], player.getLocation());
                    player.sendMessage(Utils.substituteSectionSign("&aSet your home."));
                    return true;
                case "rm":
                case "remove":
                case "del":
                case "delete":
                    if (!CommandUtils.playerHasPermission(player, "taterutils.command.delhome")) {
                        return true;
                    }
                    if (args.length == 1) {
                        player.sendMessage(Utils.substituteSectionSign("&cInvalid arguments."));
                        return true;
                    }
                    api.deleteHome(player, args[1]);
                    player.sendMessage(Utils.substituteSectionSign("&aDeleted your home."));
                    return true;
                case "list":
                    if (!CommandUtils.playerHasPermission(player, "taterutils.command.home.list")) {
                        return true;
                    }
                    player.sendMessage(Utils.substituteSectionSign("&aAvailable Homes: &e" + api.getHomes(player).toString()));
                default:
                    if (!api.teleportHome(player, args[0])) {
                        player.sendMessage(Utils.substituteSectionSign("&cYou do not have home &e" + args[0] + " &cset."));
                        return true;
                    }
                    player.sendMessage(Utils.substituteSectionSign("&aTeleported to home &e" + args[0] + "&a."));
                    return true;
            }
        }
    }
}
