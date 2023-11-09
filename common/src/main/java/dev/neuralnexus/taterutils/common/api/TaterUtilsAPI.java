package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;

/**
 * API wrapper class
 */
public class TaterUtilsAPI {
    private final HomeAPI homeAPI;

    public TaterUtilsAPI() {
        this.homeAPI = new HomeAPI();
    }

    /**
     * Get the home API.
     * @return The home API.
     */
    public HomeAPI getHomeAPI() {
        return homeAPI;
    }
}
