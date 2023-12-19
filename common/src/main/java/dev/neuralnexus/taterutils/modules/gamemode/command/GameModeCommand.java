package dev.neuralnexus.taterutils.modules.gamemode.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;

import java.util.Optional;

/** Ping Command. */
public class GameModeCommand implements Command {
    private String name = "gamemode";

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
        return "Change the gamemode of a player.";
    }

    @Override
    public String getUsage() {
        return "/gamemode <mode> [player]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.gamemode";
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, getPermission())) {
            return true;
        }
        Player player = null;
        GameMode gameMode = GameMode.UNKNOWN;
        String message = "";

        if (label.equals(getName())) {
            if (args.length == 0) {
                sender.sendMessage(getUsage());
                return true;
            } else if (args.length == 1) {
                if (sender.isPlayer()) {
                    player = (Player) sender;
                    gameMode = GameMode.from(args[0]);
                    message = TaterUtilsConfig.GameModeConfig.getMessage("changedSelf");
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.GameModeConfig.getMessage("notPlayer"));
                    return true;
                }
            } else if (args.length == 2) {
                if (!CommandUtils.senderHasPermission(sender, getPermission() + ".others")) {
                    return true;
                }
                Optional<Player> optionalPlayer =
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(p -> p.getName().equalsIgnoreCase(args[1]))
                                .findFirst();
                if (optionalPlayer.isPresent()) {
                    player = optionalPlayer.get();
                    gameMode = GameMode.from(args[0]);
                    message = TaterUtilsConfig.GameModeConfig.getMessage("changedOther");
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.GameModeConfig.getMessage("playerNotFound"));
                    return true;
                }
            } else {
                sender.sendMessage(getUsage());
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
                if (!CommandUtils.senderHasPermission(sender, getPermission() + ".others")) {
                    return true;
                }
                Optional<Player> optionalPlayer =
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(p -> p.getName().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (optionalPlayer.isPresent()) {
                    player = optionalPlayer.get();
                    message = TaterUtilsConfig.GameModeConfig.getMessage("changedOther");
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.GameModeConfig.getMessage("playerNotFound"));
                    return true;
                }
            } else {
                if (sender.isPlayer()) {
                    player = (Player) sender;
                    message = TaterUtilsConfig.GameModeConfig.getMessage("changedSelf");
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.GameModeConfig.getMessage("notPlayer"));
                    return true;
                }
            }
        }

        if (gameMode == GameMode.UNKNOWN) {
            CommandUtils.sendMessage(
                    sender, TaterUtilsConfig.GameModeConfig.getMessage("invalidGameMode"));
        } else {
            if (sender.hasPermission(getPermission() + "." + gameMode.getName())) {
                player.setGameMode(gameMode);
                CommandUtils.sendMessage(
                        sender,
                        player.parsePlaceholders(message)
                                .parseString("gamemode", gameMode.getName())
                                .getResult());
                return true;
            } else {
                CommandUtils.sendMessage(
                        sender,
                        new PlaceholderParser(
                                        TaterUtilsConfig.GameModeConfig.getMessage(
                                                "noPermissionGamemode"))
                                .parseString("gamemode", gameMode.getName())
                                .getResult());
            }
        }
        return true;
    }
}
