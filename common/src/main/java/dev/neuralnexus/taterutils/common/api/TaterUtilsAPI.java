package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterutils.common.modules.alert.api.AlertAPI;
import dev.neuralnexus.taterutils.common.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.common.modules.orewatcher.api.OreWatcherAPI;
import dev.neuralnexus.taterutils.common.modules.send.api.SendAPI;
import dev.neuralnexus.taterutils.common.modules.spawn.api.SpawnAPI;
import dev.neuralnexus.taterutils.common.modules.tpa.api.TpaAPI;
import dev.neuralnexus.taterutils.common.modules.warp.api.WarpAPI;

/** API wrapper class */
public class TaterUtilsAPI {
    private final AlertAPI alertAPI;
    private final HomeAPI homeAPI;
    private final OreWatcherAPI oreWatcherAPI;
    private final SendAPI sendAPI;
    private final SpawnAPI spawnAPI;
    private final TpaAPI tpaAPI;
    private final WarpAPI warpAPI;

    public TaterUtilsAPI() {
        this.alertAPI = new AlertAPI();
        this.homeAPI = new HomeAPI();
        this.oreWatcherAPI = new OreWatcherAPI();
        this.sendAPI = new SendAPI();
        this.spawnAPI = new SpawnAPI();
        this.tpaAPI = new TpaAPI();
        this.warpAPI = new WarpAPI();
    }

    /**
     * Get the alert API.
     *
     * @return The alert API.
     */
    public AlertAPI getAlertAPI() {
        return alertAPI;
    }

    /**
     * Get the home API.
     *
     * @return The home API.
     */
    public HomeAPI getHomeAPI() {
        return homeAPI;
    }

    /**
     * Get the ore watcher API.
     *
     * @return The ore watcher API.
     */
    public OreWatcherAPI getOreWatcherAPI() {
        return oreWatcherAPI;
    }

    /**
     * Get the send API.
     *
     * @return The send API.
     */
    public SendAPI getSendAPI() {
        return sendAPI;
    }

    /**
     * Get the spawn API.
     *
     * @return The spawn API.
     */
    public SpawnAPI getSpawnAPI() {
        return spawnAPI;
    }

    /**
     * Get the tpa API.
     *
     * @return The tpa API.
     */
    public TpaAPI getTpaAPI() {
        return tpaAPI;
    }

    /**
     * Get the warp API.
     *
     * @return The warp API.
     */
    public WarpAPI getWarpAPI() {
        return warpAPI;
    }
}
