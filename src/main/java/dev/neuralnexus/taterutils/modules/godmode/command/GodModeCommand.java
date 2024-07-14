/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.godmode.command;

import dev.neuralnexus.taterapi.TaterAPIProvider;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.GodModeConfig;
import dev.neuralnexus.taterutils.modules.godmode.api.GodModeAPI;

import java.util.Optional;

/** Ping Command. */
public class GodModeCommand implements Command {
    private String name = "godmode";

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
        return "Toggle godmode for a player";
    }

    @Override
    public String usage() {
        return "/godmode [player]";
    }

    @Override
    public String permission() {
        return "taterutils.command.godmode";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }

        GodModeConfig config = TaterUtilsConfigLoader.config().godMode();
        if (args.length == 0) {
            if (sender.isPlayer()) {
                GodModeAPI godModeAPI = TaterUtilsAPIProvider.get().godModeAPI();
                boolean godMode = !godModeAPI.getGodMode((Player) sender);
                godModeAPI.setGodMode((Player) sender, godMode);

                CommandUtils.sendMessage(
                        sender,
                        ((Player) sender)
                                .parsePlaceholders(config.toggleSelf())
                                .parseString("state", godMode ? "Enabled" : "Disabled")
                                .getResult());
            } else {
                CommandUtils.sendMessage(sender, config.specifyPlayer());
            }
        } else {
            if (sender.hasPermission(permission() + ".others")) {
                Optional<Player> target =
                        TaterAPIProvider.api().get().server().onlinePlayers().stream()
                                .filter(player -> player.name().equalsIgnoreCase(args[0]))
                                .findFirst()
                                .map(player -> (Player) player);
                if (target.isPresent()) {
                    GodModeAPI godModeAPI = TaterUtilsAPIProvider.get().godModeAPI();
                    boolean godMode = !godModeAPI.getGodMode(target.get());
                    godModeAPI.setGodMode(target.get(), godMode);

                    CommandUtils.sendMessage(
                            sender,
                            ((Player) sender)
                                    .parsePlaceholders(config.toggleOther())
                                    .parseString("state", godMode ? "Enabled" : "Disabled")
                                    .getResult());
                } else {
                    CommandUtils.sendMessage(sender, config.playerNotFound());
                }
            } else {
                CommandUtils.sendMessage(sender, config.godmodeOtherDenied());
            }
        }
        return true;
    }
}
