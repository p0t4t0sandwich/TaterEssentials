/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.home.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.placeholder.PlaceholderParser;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.HomeConfig;
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
        HomeConfig config = TaterUtilsConfigLoader.config().home();
        HomeAPI api = TaterUtilsAPIProvider.get().homeAPI();

        String message;
        if (args.length == 0) {
            message = config.noName();
        } else {
            api.setHome(player, args[0], player.location());
            message =
                    new PlaceholderParser(config.deleteHome())
                            .parseString("home", args[0])
                            .getResult();
        }
        player.sendMessage(message);
        return true;
    }
}
