package dev.neuralnexus.taterutils.common.modules.home.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.TaterUtilsConfig;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.common.api.CommandUtils;

/** DelHome Command. */
public class DelHomeCommand implements Command {
    private String name = "delhome";

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
        return "Allows players to delete a home.";
    }

    @Override
    public String getUsage() {
        return "/delhome <name>";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.delhome";
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
            api.setHome(player, args[0], player.getLocation());
            message =
                    new PlaceholderParser(TaterUtilsConfig.HomeConfig.getMessage("delhome.success"))
                            .parseString("home", args[0])
                            .getResult();
        }
        player.sendMessage(message);
        return true;
    }
}
