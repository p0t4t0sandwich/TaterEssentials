package dev.neuralnexus.taterutils.modules.spawn.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.Sender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;

/** SetSpawn Command. */
public class SetSpawnCommand implements Command {
    private String name = "setspawn";

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
        return "Sets the spawn location!";
    }

    @Override
    public String getUsage() {
        return "/setspawn";
    }

    @Override
    public String getPermission() {
        return "taterutils.command.setspawn";
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
        api.setSpawn(player.getLocation());
        CommandUtils.sendMessage(
                player, TaterUtilsConfig.SpawnConfig.getMessage("setSpawn.success"));
        return true;
    }
}
