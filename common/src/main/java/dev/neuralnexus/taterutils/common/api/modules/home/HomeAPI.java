package dev.neuralnexus.taterutils.common.api.modules.home;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.api.modules.NamedLocation;
import dev.neuralnexus.taterutils.common.storage.home.FSHomeStorage;
import dev.neuralnexus.taterutils.common.storage.home.HomeStorage;

import java.util.Optional;
import java.util.Set;

/**
 * API for the home module.
 */
public class HomeAPI {
    private final HomeStorage database;

    public HomeAPI() {
        this.database = new FSHomeStorage(new Database.DatabaseConfig(TaterUtils.Constants.PROJECT_NAME, 0, "homeData", "", ""));
    }

    /**
     * Get a player's home.
     * @param player The player.
     * @param home   The name of the home.
     */
    public Optional<NamedLocation> getHome(Player player, String home) {
        return this.database.getHome(player, home);
    }

    /**
     * Set a player's home.
     * @param player   The player.
     * @param home     The name of the home.
     * @param location The location of the home.
     */
    public void setHome(Player player, String home, Location location) {
        this.database.setHome(player, home, location);
    }

    /**
     * Delete a player's home.
     * @param player The player.
     * @param home   The name of the home.
     */
    public void deleteHome(Player player, String home) {
        this.database.deleteHome(player, home);
    }

    /**
     * Get all of a player's homes.
     * @param player The player.
     */
    public Set<NamedLocation> getHomes(Player player) {
        return this.database.getHomes(player);
    }

    /**
     * Teleport a player to their home.
     * @param player The player.
     */
    public boolean teleportHome(Player player, String home) {
        return this.database.teleportHome(player, home);
    }

    /**
     * Get list of invalid home names.
     * @return The list of invalid home names.
     */
    public Set<String> getInvalidHomeNames() {
        Set<String> invalidHomeNames = new java.util.HashSet<>();
        invalidHomeNames.add("add");
        invalidHomeNames.add("set");
        invalidHomeNames.add("rm");
        invalidHomeNames.add("remove");
        invalidHomeNames.add("del");
        invalidHomeNames.add("delete");
        invalidHomeNames.add("list");
        return invalidHomeNames;
    }
}
