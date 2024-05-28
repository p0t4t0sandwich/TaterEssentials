package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.home.api.HomeAPI;

/** Home Command. */
public class HomeCommand implements Command {
    private String name = "home";

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
        return "Allows players to set and teleport to their home.";
    }

    @Override
    public String usage() {
        return "/home [name | del | set | list] [name]";
    }

    @Override
    public String permission() {
        return "taterutils.command.home";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        HomeAPI api = TaterUtilsAPIProvider.get().homeAPI();

        String message = "";
        if (args.length == 0) {
            if (!api.teleportHome(player, "home")) {
                message =
                        new PlaceholderParser(
                                        TaterUtilsConfigOld.HomeConfig.getMessage(
                                                "home.homeNotSet"))
                                .parseString("home", "home")
                                .getResult();
            } else {
                message =
                        new PlaceholderParser(
                                        TaterUtilsConfigOld.HomeConfig.getMessage(
                                                "home.teleportedToHome"))
                                .parseString("home", "home")
                                .getResult();
            }
        } else {
            switch (args[0]) {
                case "add":
                case "create":
                case "set":
                    if (!CommandUtils.senderHasPermission(player, "taterutils.command.sethome")) {
                        break;
                    }
                    if (args.length == 1) {
                        message =
                                TaterUtilsConfigOld.HomeConfig.getMessage("home.invalidArguments");
                        break;
                    }
                    if (api.getInvalidHomeNames().contains(args[1])) {
                        message = TaterUtilsConfigOld.HomeConfig.getMessage("home.invalidHomeName");
                        break;
                    }
                    api.setHome(player, args[1], player.location());
                    message =
                            new PlaceholderParser(
                                            TaterUtilsConfigOld.HomeConfig.getMessage(
                                                    "setHome.success"))
                                    .parseString("home", args[1])
                                    .getResult();
                    break;
                case "rm":
                case "remove":
                case "del":
                case "delete":
                    if (!CommandUtils.senderHasPermission(player, "taterutils.command.delhome")) {
                        break;
                    }
                    if (args.length == 1) {
                        message = TaterUtilsConfigOld.HomeConfig.getMessage("home.noName");
                        break;
                    }
                    api.deleteHome(player, args[1]);
                    message =
                            new PlaceholderParser(
                                            TaterUtilsConfigOld.HomeConfig.getMessage(
                                                    "delhome.success"))
                                    .parseString("home", args[1])
                                    .getResult();
                    break;
                case "list":
                    if (!CommandUtils.senderHasPermission(player, "taterutils.command.home.list")) {
                        break;
                    }
                    message =
                            new PlaceholderParser(
                                            TaterUtilsConfigOld.HomeConfig.getMessage(
                                                    "home.availableHomes"))
                                    .parseString("homes", api.getHomes(player).toString())
                                    .getResult();
                    break;
                default:
                    if (!api.teleportHome(player, args[0])) {
                        message =
                                new PlaceholderParser(
                                                TaterUtilsConfigOld.HomeConfig.getMessage(
                                                        "home.homeNotSet"))
                                        .parseString("home", "home")
                                        .getResult();
                        break;
                    }
                    message =
                            new PlaceholderParser(
                                            TaterUtilsConfigOld.HomeConfig.getMessage(
                                                    "home.teleportedToHome"))
                                    .parseString("home", args[0])
                                    .getResult();
                    break;
            }
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
