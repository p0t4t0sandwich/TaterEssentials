/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.HomeConfig;
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
        HomeConfig config = TaterUtilsConfigLoader.config().home();
        HomeAPI api = TaterUtilsAPIProvider.get().homeAPI();

        String message;
        if (args.length == 0) {
            message = config.noName();
        } else {
            if (api.getInvalidHomeNames().contains(args[0])) {
                message = config.invalidHomeName();
            } else {
                api.setHome(player, args[0], player.location());
                message =
                        new PlaceholderParser(config.setHome())
                                .parseString("home", args[0])
                                .getResult();
            }
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
