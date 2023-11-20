package dev.neuralnexus.taterutils.common.modules.spawn.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterutils.common.TaterUtilsConfig;
import dev.neuralnexus.taterutils.common.api.CommandUtils;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.spawn.api.SpawnAPI;

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
