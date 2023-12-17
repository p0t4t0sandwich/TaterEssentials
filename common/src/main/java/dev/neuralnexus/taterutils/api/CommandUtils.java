package dev.neuralnexus.taterutils.api;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.command.Sender;

/** General command utilities. */
public class CommandUtils {
    /**
     * Send a message to the sender.
     *
     * @param sender The sender.
     * @param message The message.
     */
    public static void sendMessage(Sender sender, String message) {
        sender.sendMessage(Utils.substituteSectionSign(message));
    }

    /**
     * Checks if the sender is a player.
     *
     * @param sender The sender.
     * @return True if the sender is a player.
     */
    public static boolean senderIsPlayer(Sender sender) {
        if (!(sender.isPlayer())) {
            sendMessage(sender, "&cOnly players can use this command.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the player has permission.
     *
     * @param player The player.
     * @param permission The permission.
     * @return True if the player has permission.
     */
    public static boolean senderHasPermission(Sender player, String permission) {
        if (!player.hasPermission(permission)) {
            sendMessage(player, "&cYou do not have permission to use this command.");
            return false;
        }
        return true;
    }

    /**
     * Checks if the sender is a player and has permission.
     *
     * @param sender The sender.
     * @param permission The permission.
     */
    public static boolean senderIsPlayerAndHasPermission(Sender sender, String permission) {
        if (!senderIsPlayer(sender)) {
            return false;
        }
        return senderHasPermission(sender, permission);
    }
}
