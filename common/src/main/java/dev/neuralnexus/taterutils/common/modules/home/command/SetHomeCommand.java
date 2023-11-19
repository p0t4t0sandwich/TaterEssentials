package dev.neuralnexus.taterutils.common.modules.home.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.TaterUtilsConfig;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.common.api.CommandUtils;

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
