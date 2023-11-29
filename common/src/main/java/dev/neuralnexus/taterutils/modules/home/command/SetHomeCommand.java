package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.home.api.HomeAPI;

/** SetHome Command. */
public class SetHomeCommand implements Command {
    private String name = "sethome";

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
        return "Allows players to set a home.";
    }

    @Override
    public String getUsage() {
        return "/sethome <name>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.sethome";
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

        String message;
        if (args.length == 0) {
            message = TaterUtilsConfig.HomeConfig.getMessage("home.noName");
        } else {
            if (api.getInvalidHomeNames().contains(args[1])) {
                message = TaterUtilsConfig.HomeConfig.getMessage("home.invalidName");
            } else {
                api.setHome(player, args[0], player.getLocation());
                message =
                        new PlaceholderParser(
                                        TaterUtilsConfig.HomeConfig.getMessage("setHome.success"))
                                .parseString("name", args[0])
                                .getResult();
            }
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
