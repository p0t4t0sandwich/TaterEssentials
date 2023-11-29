package dev.neuralnexus.taterutils.modules.spawn.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;

/** DelSpawn Command. */
public class DelSpawnCommand implements Command {
    private String name = "delspawn";

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
        CommandUtils.sendMessage(
                player, TaterUtilsConfig.SpawnConfig.getMessage("delSpawn.success"));
        return true;
    }
}
