package dev.neuralnexus.taterutils.modules.spawn.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfigOld;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;

/** Spawn Command. */
public class SpawnCommand implements Command {
    private String name = "spawn";

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
        return "Teleports you to spawn.";
    }

    @Override
    public String usage() {
        return "/spawn";
    }

    @Override
    public String permission() {
        return "taterutils.command.spawn";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        SpawnAPI api = TaterUtilsAPIProvider.get().spawnAPI();

        String message;
        if (!api.teleportSpawn(player)) {
            message = TaterUtilsConfigOld.SpawnConfig.getMessage("spawnNotSet");
        } else {
            message = TaterUtilsConfigOld.SpawnConfig.getMessage("teleportedToSpawn");
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
