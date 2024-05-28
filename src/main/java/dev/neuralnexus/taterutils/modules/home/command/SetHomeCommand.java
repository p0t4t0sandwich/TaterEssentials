package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.home.api.HomeAPI;

/** SetHome Command. */
public class SetHomeCommand implements Command {
    private String name = "sethome";

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
        return "Allows players to set a home.";
    }

    @Override
    public String usage() {
        return "/sethome <name>";
    }

    @Override
    public String permission() {
        return "taterutils.command.sethome";
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
            message = TaterUtilsConfigOld.HomeConfig.getMessage("home.noName");
        } else {
            if (api.getInvalidHomeNames().contains(args[0])) {
                message = TaterUtilsConfigOld.HomeConfig.getMessage("home.invalidName");
            } else {
                api.setHome(player, args[0], player.location());
                message =
                        new PlaceholderParser(
                                        TaterUtilsConfigOld.HomeConfig.getMessage(
                                                "setHome.success"))
                                .parseString("home", args[0])
                                .getResult();
            }
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
