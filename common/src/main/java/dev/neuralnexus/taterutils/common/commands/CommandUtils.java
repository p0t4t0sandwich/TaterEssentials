package dev.neuralnexus.taterutils.common.commands;

import dev.neuralnexus.taterlib.common.Utils;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * General command utilities.
 */
public class CommandUtils {
    /**
     * Checks if the sender is a player.
     * @param sender The sender.
     * @return True if the sender is a player.
     */
    public static boolean senderIsPlayer(Sender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.substituteSectionSign("&cOnly players can use this command."));
            return false;
        }
        return true;
    }

    /**
     * Checks if the player has permission.
     * @param player The player.
     * @param permission The permission.
     * @return True if the player has permission.
     */
    public static boolean playerHasPermission(Player player, String permission) {
        if (!player.hasPermission(permission)) {
            player.sendMessage(Utils.substituteSectionSign("&cYou do not have permission to use this command."));
            return false;
        }
        return true;
    }

    /**
     * Checks if the sender is a player and has permission.
     * @param sender The sender.
     * @param permission The permission.
     */
    public static boolean senderIsPlayerAndHasPermission(Sender sender, String permission) {
        if (!senderIsPlayer(sender)) {
            return false;
        }
        if (!playerHasPermission((Player) sender, permission)) {
            return false;
        }
        return true;
    }
}
