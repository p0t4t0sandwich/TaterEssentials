package dev.neuralnexus.taterutils.common.api.modules;

import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;

/**
 * API for the home module.
 */
public class HomeAPI {
    private final Data data;
    private final Database database;

    public HomeAPI() {
        this.data = new Data();
        this.database = new Filesystem(new Database.DatabaseConfig("homeData", 0, "home", "", ""));
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
