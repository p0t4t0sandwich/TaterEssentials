package dev.neuralnexus.taterutils.common.commands.spawn;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.api.modules.spawn.SpawnAPI;
import dev.neuralnexus.taterutils.common.commands.CommandUtils;

/**
 * Spawn Command.
 */
public class SpawnCommand implements Command {
    private String name = "spawn";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "Teleports you to spawn.";
    }

    @Override
    public String getUsage() {
        return "/spawn";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.spawn";
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
        SpawnAPI api = TaterUtilsAPIProvider.get().getSpawnAPI();

        String message;
        if (!api.teleportSpawn(player)) {
            message = "&cSpawn has not been set.";
        } else {
            message = "&aTeleported to spawn.";
        }
        CommandUtils.sendMessage(player, message);
        return true;
    }
}
