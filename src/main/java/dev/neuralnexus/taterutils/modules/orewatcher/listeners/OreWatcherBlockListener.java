package dev.neuralnexus.taterutils.modules.orewatcher.listeners;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.block.PlayerBlockBreakEvent;
import dev.neuralnexus.taterlib.placeholder.PlaceholderParser;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;
import dev.neuralnexus.taterutils.config.sections.OreWatcherConfig;
import dev.neuralnexus.taterutils.modules.orewatcher.api.OreWatcherAPI;

/** OreWatcher block listener. */
public class OreWatcherBlockListener {
    public static void onBlockBreak(PlayerBlockBreakEvent event) {
        OreWatcherConfig config = TaterUtilsConfigLoader.config().oreWatcher();
        OreWatcherAPI api = TaterUtilsAPIProvider.get().oreWatcherAPI();
        if (event.block().type().toLowerCase().contains("ore")) {
            api.addOreMined(event.player(), 1);
            api.getOreMined(event.player())
                    .ifPresent(
                            oreMined -> {
                                // Wait 60 seconds before checking if the player is mining at a rate
                                // over the threshold
                                if (oreMined.getTimeDelta() < 60000) {
                                    return;
                                }

                                // Check if the player is mining at a rate over the threshold
                                if (oreMined.getAveragePerMinute() >= config.alertThreshold()) {
                                    // Cancel the event
                                    if (config.cancelMinedOverThreshold()) {
                                        event.setCancelled(true);
                                    }

                                    // Log the event to the console
                                    String adminAlertMessage =
                                            new PlaceholderParser(
                                                            "["
                                                                    + TaterUtils.PROJECT_NAME
                                                                    + "->OreWatcher]"
                                                                    + config.adminAlertMessage())
                                                    .parseString(
                                                            "playername", event.player().name())
                                                    .parseString("blockname", event.block().type())
                                                    .parseString(
                                                            "oreperminute",
                                                            String.valueOf(
                                                                    oreMined.getAveragePerMinute()))
                                                    .parseSectionSign()
                                                    .getResult();
                                    TaterUtils.logger().info(Utils.ansiParser(adminAlertMessage));

                                    // Send the message to all players with the permission
                                    if (config.adminAlertEnabled()) {
                                        TaterAPIProvider.get().getServer().onlinePlayers().stream()
                                                .filter(
                                                        player ->
                                                                player.hasPermission(
                                                                        "taterutils.module.orewatcher.admin"))
                                                .forEach(
                                                        player ->
                                                                player.sendMessage(
                                                                        adminAlertMessage));
                                    }

                                    // Send the message to the player
                                    if (config.playerAlertEnabled()) {
                                        String playerAlertMessage =
                                                new PlaceholderParser(
                                                                "["
                                                                        + TaterUtils.PROJECT_NAME
                                                                        + "->OreWatcher]"
                                                                        + config
                                                                                .playerAlertMessage())
                                                        .parseString(
                                                                "playername", event.player().name())
                                                        .parseString(
                                                                "blockname", event.block().type())
                                                        .parseString(
                                                                "oreperminute",
                                                                String.valueOf(
                                                                        oreMined
                                                                                .getAveragePerMinute()))
                                                        .parseSectionSign()
                                                        .getResult();
                                        event.player().sendMessage(playerAlertMessage);
                                    }
                                }
                            });
        }
    }
}
