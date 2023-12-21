package dev.neuralnexus.taterutils.modules.godmode.storage;

import dev.neuralnexus.taterlib.player.Player;

/** GodMode API for the spawn module. */
public interface GodModeStorage {
    /**
     * Get player's godmode status.
     *
     * @param player The player.
     * @return The player's godmode status.
     */
    boolean getGodMode(Player player);

    /**
     * Set player's godmode status.
     *
     * @param player The player.
     * @param godMode The player's godmode status.
     */
    void setGodMode(Player player, boolean godMode);
}
