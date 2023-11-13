package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.common.modules.send.api.SendAPI;
import dev.neuralnexus.taterutils.common.modules.spawn.api.SpawnAPI;
import dev.neuralnexus.taterutils.common.modules.tpa.api.TpaAPI;
import dev.neuralnexus.taterutils.common.modules.warp.api.WarpAPI;

/**
 * API wrapper class
 */
public class TaterUtilsAPI {
    private final HomeAPI homeAPI;
    private final SendAPI sendAPI;
    private final SpawnAPI spawnAPI;
    private final TpaAPI tpaAPI;
    private final WarpAPI warpAPI;

    public TaterUtilsAPI() {
        this.homeAPI = new HomeAPI();
        this.sendAPI = new SendAPI();
        this.spawnAPI = new SpawnAPI();
        this.tpaAPI = new TpaAPI();
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
     * Get the send API.
     * @return The send API.
     */
    public SendAPI getSendAPI() {
        return sendAPI;
    }

    /**
     * Get the spawn API.
     * @return The spawn API.
     */
    public SpawnAPI getSpawnAPI() {
        return spawnAPI;
    }

    /**
     * Get the tpa API.
     * @return The tpa API.
     */
    public TpaAPI getTpaAPI() {
        return tpaAPI;
    }

    /**
     * Get the warp API.
     * @return The warp API.
     */
    public WarpAPI getWarpAPI() {
        return warpAPI;
    }
}
