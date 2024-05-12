package dev.neuralnexus.taterutils.modules.spawn.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterutils.TaterUtilsConfig;
import dev.neuralnexus.taterutils.api.CommandUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;

/** SetSpawn Command. */
public class SetSpawnCommand implements Command {
    private String name = "setspawn";

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
        return "Sets the spawn location!";
    }

    @Override
    public String usage() {
        return "/setspawn";
    }

    @Override
    public String permission() {
        return "taterutils.command.setspawn";
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!CommandUtils.senderIsPlayerAndHasPermission(sender, permission())) {
            return true;
        }
        Player player = (Player) sender;
        SpawnAPI api = TaterUtilsAPIProvider.get().spawnAPI();
        api.setSpawn(player.location());
        CommandUtils.sendMessage(
                player, TaterUtilsConfig.SpawnConfig.getMessage("setSpawn.success"));
        return true;
    }
}
