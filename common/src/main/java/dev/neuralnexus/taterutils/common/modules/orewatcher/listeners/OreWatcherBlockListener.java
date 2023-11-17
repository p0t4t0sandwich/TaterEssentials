package dev.neuralnexus.taterutils.common.modules.orewatcher.listeners;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.common.event.block.BlockBreakEvent;
import dev.neuralnexus.taterlib.common.placeholder.PlaceholderParser;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.TaterUtilsConfig;
import dev.neuralnexus.taterutils.common.api.TaterUtilsAPIProvider;
import dev.neuralnexus.taterutils.common.modules.orewatcher.api.OreWatcherAPI;

/**
 * OreWatcher block listener.
 */
public class OreWatcherBlockListener {
    public static void onBlockBreak(BlockBreakEvent event) {
        OreWatcherAPI api = TaterUtilsAPIProvider.get().getOreWatcherAPI();
        if (event.getBlock().getType().toLowerCase().contains("ore")) {
            api.addOreMined(event.getPlayer(), 1);
            api.getOreMined(event.getPlayer()).ifPresent(oreMined -> {
                // Wait 60 seconds before checking if the player is mining at a rate over the threshold
                if (oreMined.getTimeDelta() < 60000) {
                    return;
                }

                // Check if the player is mining at a rate over the threshold
                if (oreMined.getAveragePerMinute() >= TaterUtilsConfig.OreWatcherConfig.getAlertThreshold()) {
                    // Cancel the event
                    if (TaterUtilsConfig.OreWatcherConfig.getCancelMinedOverThreshold()) {
                        event.setCancelled(true);
                    }

                    // Log the event to the console
                    String adminAlertMessage = new PlaceholderParser("[" + TaterUtils.Constants.PROJECT_NAME + "->OreWatcher]" + TaterUtilsConfig.OreWatcherConfig.getMessage("adminAlertMessage"))
                            .parseString("playername", event.getPlayer().getName())
                            .parseString("blockname", event.getBlock().getType())
                            .parseString("oreperminute", String.valueOf(oreMined.getAveragePerMinute()))
                            .parseSectionSign()
                            .getResult();
                    TaterUtils.getLogger().info(Utils.ansiParser(adminAlertMessage));

                    // Send the message to all players with the permission
                    if (TaterUtilsConfig.OreWatcherConfig.getAdminAlertEnabled()) {
                        TaterAPIProvider.get().getServer().getOnlinePlayers().stream()
                                .filter(player -> player.hasPermission("taterutils.module.orewatcher.admin"))
                                .forEach(player -> player.sendMessage(adminAlertMessage));
                    }

                    // Send the message to the player
                    if (TaterUtilsConfig.OreWatcherConfig.getPlayerAlertEnabled()) {
                        String playerAlertMessage = new PlaceholderParser("[" + TaterUtils.Constants.PROJECT_NAME + "->OreWatcher]" + TaterUtilsConfig.OreWatcherConfig.getMessage("playerAlertMessage"))
                                .parseString("playername", event.getPlayer().getName())
                                .parseString("blockname", event.getBlock().getType())
                                .parseString("oreperminute", String.valueOf(oreMined.getAveragePerMinute()))
                                .parseSectionSign()
                                .getResult();
                        event.getPlayer().sendMessage(playerAlertMessage);
                    }
                }
            });
        }
    }
}
