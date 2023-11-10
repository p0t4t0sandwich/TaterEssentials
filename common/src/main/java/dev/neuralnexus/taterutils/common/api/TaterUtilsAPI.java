package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;
import dev.neuralnexus.taterutils.common.api.modules.WarpAPI;

/**
 * API wrapper class
 */
public class TaterUtilsAPI {
    private final HomeAPI homeAPI;
    private final WarpAPI warpAPI;
    public TaterUtilsAPI() {
        this.homeAPI = new HomeAPI();
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
     * Get the warp API.
     * @return The warp API.
     */
    public WarpAPI getWarpAPI() {
        return warpAPI;
    }
}
