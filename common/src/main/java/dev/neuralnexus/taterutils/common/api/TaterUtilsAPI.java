package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.common.modules.spawn.api.SpawnAPI;
import dev.neuralnexus.taterutils.common.modules.warp.api.WarpAPI;

/**
 * API wrapper class
 */
public class TaterUtilsAPI {
    private final HomeAPI homeAPI;
    private final SpawnAPI spawnAPI;
    private final WarpAPI warpAPI;

    public TaterUtilsAPI() {
        this.homeAPI = new HomeAPI();
        this.spawnAPI = new SpawnAPI();
        this.warpAPI = new WarpAPI();
    }

    /**
     * Get the home API.
     * @return The home API.
     */
    public HomeAPI getHomeAPI() {
        return homeAPI;
    }

    /**
     * Get the spawn API.
     * @return The spawn API.
     */
    public SpawnAPI getSpawnAPI() {
        return spawnAPI;
    }

    /**
     * Get the warp API.
     * @return The warp API.
     */
    public WarpAPI getWarpAPI() {
        return warpAPI;
    }
}
