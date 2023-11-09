package dev.neuralnexus.taterutils.common.api.modules;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.storage.FSHomeStorage;
import dev.neuralnexus.taterutils.common.storage.FSWarpStorage;
import dev.neuralnexus.taterutils.common.storage.HomeStorage;
import dev.neuralnexus.taterutils.common.storage.WarpStorage;

import java.util.Optional;
import java.util.Set;

public class WarpAPI {
    private final WarpAPI.Data data;
    private final WarpStorage database;

    public WarpAPI() {
        this.data = new WarpAPI().data;
        this.database = new FSWarpStorage(new Database.DatabaseConfig(TaterUtils.Constants.PROJECT_NAME, 0, "warpData", "", ""));
    }

    /**
     * Get a player's home.
     * @param warp   The name of the home.
     */
    public Optional<WarpAPI.WarpLocation> getWarp(String warp) {
        return this.database.getWarp(warp);
    }

    /**
     * Set a Warp.
     * @param player   The player.
     * @param location The location of the home.
     */
    public void setWarp(Player player, String warp, Location location) {
        this.database.setWarp(player, warp, location);
    }

    /**
     * Delete a Warp.
     * @param warp   The name of the home.
     */
    public void deleteWarp(String warp) {
        this.database.deleteWarp(warp);
    }

    /**
     * Get all Warps.
     */
    public Set<WarpAPI.WarpLocation> getWarps() {
        return this.database.getWarps();
    }

    /**
     * Teleport a player to a Warp.
     * @param player The player.
     */
    public boolean teleportWarp(Player player, String warp) {
        return this.database.teleportWarp(player, warp);
    }

    /**
     * Data used throughout the plugin via the API.
     */
    static class Data {
        Data() {}
    }

    /**
     * A Warp Location.
     */
    public static class WarpLocation {
        public final String name;
        public final String world;
        public final double x;
        public final double y;
        public final double z;
        public final float yaw;
        public final float pitch;
        public WarpLocation(String name, String world, double x, double y, double z, float yaw, float pitch) {
            this.name = name;
            this.world = world;
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        /**
         * Get the location of the warp.
         * @return The location of the warp.
         */
        public Location getLocation() {
            return new AbstractLocation(world, x, y, z, yaw, pitch);
        }
    }
}
