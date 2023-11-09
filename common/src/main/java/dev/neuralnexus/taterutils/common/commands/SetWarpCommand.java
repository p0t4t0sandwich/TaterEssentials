package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;
import dev.neuralnexus.taterutils.common.api.modules.WarpAPI;

/**
 * SetWarp Command.
 */
public class SetWarpCommand implements Command {
    private String name = "setwarp";

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
        return "Sets a warp location!";
    }

    @Override
    public String getUsage() {
        return "/setwarp [set/delete] [name]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.setwarp";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
            /**
             * CONFIRMING SENDER IS PLAYER
             */
            if (!(sender instanceof Player)) {
                sender.sendMessage(PlaceholderParser.substituteSectionSign("&cOnly players can use this command."));
                return true;
            }
            Player player = (Player) sender;
            /**
             * CONFIRMING SENDER HAS REQUIRED PERMS
             */
            if (!player.hasPermission(getPermission())) {
                player.sendMessage(PlaceholderParser.substituteSectionSign("&cYou do not have permission to use this command."));
                return true;
            }
            /**
             * SET THE WARP BOISSS
             */
            WarpAPI api = TaterUtilsAPIProvider.get().getWarpAPI();

            if (args.length == 0) {
                player.sendMessage(PlaceholderParser.substituteSectionSign("&aPlease Provide a Warp Name!"));
                return true;
            } else {
                switch (args[0]) {
                    case "set":
                        if (args.length == 1) {
                            player.sendMessage(PlaceholderParser.substituteSectionSign("&cInvalid arguments."));
                            return true;
                        }
                        api.setWarp(player, args[1], player.getLocation());
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&aSet the warp"));
                        return true;
                    case "delete":
                        if (args.length == 1) {
                            player.sendMessage(PlaceholderParser.substituteSectionSign("&cInvalid arguments."));
                            return true;
                        }
                        api.deleteWarp(args[1]);
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&aDeleted the warp."));
                        return true;
                    default:
                        player.sendMessage(PlaceholderParser.substituteSectionSign("&cInvalid arguments."));
                        return true;
                }
            }
    }

}
