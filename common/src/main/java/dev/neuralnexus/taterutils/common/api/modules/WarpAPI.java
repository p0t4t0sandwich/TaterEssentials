package dev.neuralnexus.taterutils.common.api.modules;

import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.storage.Filesystem;
import dev.neuralnexus.taterlib.common.utils.Location;

public class WarpAPI {
    private final WarpAPI.Data data;
    private final Database database;

    public WarpAPI() {
        this.data = new WarpAPI().data;
        this.database = new Filesystem(new Database.DatabaseConfig("warpData", 0, "warp", "", ""));
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
         * Get the location of the home.
         * @return The location of the home.
         */
        public Location getLocation() {
            return new AbstractLocation(world, x, y, z, yaw, pitch);
        }
    }
}
