package dev.neuralnexus.taterutils.common.api.modules;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.storage.FSHomeStorage;
import dev.neuralnexus.taterutils.common.storage.HomeStorage;

import java.util.Optional;
import java.util.Set;

/**
 * API for the home module.
 */
public class HomeAPI {
    private final Data data;
    private final HomeStorage database;

    public HomeAPI() {
        this.data = new Data();
        this.database = new FSHomeStorage(new Database.DatabaseConfig(TaterUtils.Constants.PROJECT_NAME, 0, "homeData", "", ""));
    }

    /**
     * Get a player's home.
     * @param player The player.
     * @param home   The name of the home.
     */
    public Optional<PlayerHome> getHome(Player player, String home) {
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
    public Set<PlayerHome> getHomes(Player player) {
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
     * Data used throughout the plugin via the API.
     */
    static class Data {
        Data() {}
    }

    /**
     * A player's home.
     */
    public static class PlayerHome {
        public final String name;
        public final String world;
        public final double x;
        public final double y;
        public final double z;
        public final float yaw;
        public final float pitch;
        public PlayerHome(String name, String world, double x, double y, double z, float yaw, float pitch) {
            this.name = name;
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        /**
         * Get the location of the home.
         * @return The location of the home.
         */
        public Location getLocation() {
            return new AbstractLocation(world, x, y, z, yaw, pitch);
        }
    }
}
