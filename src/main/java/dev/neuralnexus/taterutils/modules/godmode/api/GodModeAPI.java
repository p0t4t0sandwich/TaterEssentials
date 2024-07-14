/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.godmode.api;

import dev.neuralnexus.taterapi.entity.player.Player;

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
