/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.gamemode.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.GameModeConfig;

import java.util.Optional;

/** Ping Command. */
public class GameModeCommand implements Command {
    private String name = "gm";

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
        return "Change the gamemode of a player.";
    }

    @Override
    public String usage() {
        return "/gm <mode> [player]";
    }

    @Override
    public String permission() {
        return "taterutils.command.gamemode";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, permission())) {
            return true;
        }
        GameModeConfig config = TaterUtilsConfigLoader.config().gameMode();
        Player player = null;
        GameMode gameMode = GameMode.UNKNOWN;
        String message = "";

        if (label.equals(name())) {
            if (args.length == 0) {
                sender.sendMessage(usage());
                return true;
            } else if (args.length == 1) {
                if (sender.isPlayer()) {
                    player = (Player) sender;
                    gameMode = GameMode.from(args[0]);
                    message = config.changedSelf();
                } else {
                    CommandUtils.sendMessage(sender, config.notPlayer());
                    return true;
                }
            } else if (args.length == 2) {
                if (!CommandUtils.senderHasPermission(sender, permission() + ".others")) {
                    return true;
                }
                Optional<Player> optionalPlayer =
                        TaterAPIProvider.get().getServer().onlinePlayers().stream()
                                .filter(p -> p.name().equalsIgnoreCase(args[1]))
                                .findFirst()
                                .map(p -> (Player) p);
                if (optionalPlayer.isPresent()) {
                    player = optionalPlayer.get();
                    gameMode = GameMode.from(args[0]);
                    message = config.changedOther();
                } else {
                    CommandUtils.sendMessage(sender, config.playerNotFound());
                    return true;
                }
            } else {
                sender.sendMessage(usage());
            }
        } else {
            switch (label) {
                case "gms":
                    gameMode = GameMode.SURVIVAL;
                    break;
                case "gmc":
                    gameMode = GameMode.CREATIVE;
                    break;
                case "gma":
                    gameMode = GameMode.ADVENTURE;
                    break;
                case "gmsp":
                    gameMode = GameMode.SPECTATOR;
                    break;
            }

            if (args.length == 1) {
                if (!CommandUtils.senderHasPermission(sender, permission() + ".others")) {
                    return true;
                }
                Optional<Player> optionalPlayer =
                        TaterAPIProvider.get().getServer().onlinePlayers().stream()
                                .filter(p -> p.name().equalsIgnoreCase(args[0]))
                                .findFirst()
                                .map(p -> (Player) p);
                if (optionalPlayer.isPresent()) {
                    player = optionalPlayer.get();
                    message = config.changedOther();
                } else {
                    CommandUtils.sendMessage(sender, config.playerNotFound());
                    return true;
                }
            } else {
                if (sender.isPlayer()) {
                    player = (Player) sender;
                    message = config.changedSelf();
                } else {
                    CommandUtils.sendMessage(sender, config.notPlayer());
                    return true;
                }
            }
        }

        if (gameMode == GameMode.UNKNOWN) {
            CommandUtils.sendMessage(sender, config.invalidGameMode());
        } else {
            if (sender.hasPermission(permission() + "." + gameMode.name())) {
                player.setGameMode(gameMode);
                CommandUtils.sendMessage(
                        sender,
                        player.parsePlaceholders(message)
                                .parseString("gamemode", gameMode.name())
                                .getResult());
                return true;
            } else {
                CommandUtils.sendMessage(
                        sender,
                        new PlaceholderParser(config.noPermissionGamemode())
                                .parseString("gamemode", gameMode.name())
                                .getResult());
            }
        }
        return true;
    }
}
