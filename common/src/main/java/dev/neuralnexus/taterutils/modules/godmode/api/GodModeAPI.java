package dev.neuralnexus.taterutils.modules.godmode.api;

import dev.neuralnexus.taterlib.player.Player;

/** API for the GodMode module. */
public class GodModeAPI {
    /**
     * Get player's godmode status.
     *
     * @param player The player.
     * @return The player's godmode status.
     */
    public boolean getGodMode(Player player) {
        return player.getMeta("godMode", Boolean.class).orElse(false);
    }

    /**
     * Set player's godmode status.
     *
     * @param player The player.
     * @param godMode The player's godmode status.
     */
    public void setGodMode(Player player, boolean godMode) {
        player.setMeta("godMode", godMode);
    }
}
