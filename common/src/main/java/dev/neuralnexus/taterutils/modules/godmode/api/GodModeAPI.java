package dev.neuralnexus.taterutils.modules.godmode.api;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.storage.Database;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.godmode.storage.FSGodModeStorage;
import dev.neuralnexus.taterutils.modules.godmode.storage.GodModeStorage;

/** API for the GodMode module. */
public class GodModeAPI {
    private final GodModeStorage database;

    public GodModeAPI() {
        this.database =
                new FSGodModeStorage(
                        new Database.DatabaseConfig(
                                TaterUtils.Constants.PROJECT_NAME, 0, "godModeData", "", ""));
    }

    /**
     * Get player's godmode status.
     *
     * @param player The player.
     * @return The player's godmode status.
     */
    public boolean getGodMode(Player player) {
        return this.database.getGodMode(player);
    }

    /**
     * Set player's godmode status.
     *
     * @param player The player.
     * @param godMode The player's godmode status.
     */
    public void setGodMode(Player player, boolean godMode) {
        this.database.setGodMode(player, godMode);
    }
}
