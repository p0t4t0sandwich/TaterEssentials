package dev.neuralnexus.taterutils.modules.godmode.command;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.godmode.api.GodModeAPI;

import java.util.Optional;

/** Ping Command. */
public class GodModeCommand implements Command {
    private String name = "godmode";

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
        return "Toggle godmode for a player";
    }

    @Override
    public String getUsage() {
        return "/godmode [player]";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.godmode";
    }

    @Override
    public String execute(String[] args) {
        return null;
    }

    @Override
    public boolean execute(Sender sender, String label, String[] args) {
        if (!CommandUtils.senderHasPermission(sender, getPermission())) {
            return true;
        }

        if (args.length == 0) {
            if (sender.isPlayer()) {
                GodModeAPI godModeAPI = TaterUtilsAPIProvider.get().getGodModeAPI();
                boolean godMode = !godModeAPI.getGodMode((Player) sender);
                godModeAPI.setGodMode((Player) sender, godMode);

                CommandUtils.sendMessage(
                        sender,
                        ((Player) sender)
                                .parsePlaceholders(
                                        TaterUtilsConfig.GodModeConfig.getMessage("toggleSelf"))
                                .parseString("state", godMode ? "Enabled" : "Disabled")
                                .getResult());
            } else {
                CommandUtils.sendMessage(
                        sender, TaterUtilsConfig.GodModeConfig.getMessage("specifyPlayer"));
            }
        } else {
            if (sender.hasPermission(getPermission() + ".others")) {
                Optional<Player> target =
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(player -> player.getName().equalsIgnoreCase(args[0]))
                                .findFirst();
                if (target.isPresent()) {
                    GodModeAPI godModeAPI = TaterUtilsAPIProvider.get().getGodModeAPI();
                    boolean godMode = !godModeAPI.getGodMode(target.get());
                    godModeAPI.setGodMode(target.get(), godMode);

                    CommandUtils.sendMessage(
                            sender,
                            ((Player) sender)
                                    .parsePlaceholders(
                                            TaterUtilsConfig.GodModeConfig.getMessage(
                                                    "toggleOther"))
                                    .parseString("state", godMode ? "Enabled" : "Disabled")
                                    .getResult());
                } else {
                    CommandUtils.sendMessage(
                            sender, TaterUtilsConfig.PingConfig.getMessage("playerNotFound"));
                }
            } else {
                CommandUtils.sendMessage(
                        sender, TaterUtilsConfig.PingConfig.getMessage("godmodeOtherDenied"));
            }
        }
        return true;
    }
}
