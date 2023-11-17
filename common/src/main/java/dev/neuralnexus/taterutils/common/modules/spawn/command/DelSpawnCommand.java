package dev.neuralnexus.taterutils.common.modules.spawn.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.TaterUtilsConfig;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.spawn.api.SpawnAPI;
import dev.neuralnexus.taterutils.common.api.CommandUtils;

/**
 * DelSpawn Command.
 */
public class DelSpawnCommand implements Command {
    private String name = "delspawn";

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
        return "Deletes the spawn location!";
    }

    @Override
    public String getUsage() {
        return "/delspawn";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.delspawn";
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
        api.deleteSpawn();
        CommandUtils.sendMessage(player, TaterUtilsConfig.SpawnConfig.getMessage("delSpawn.success"));
        return true;
    }
}
