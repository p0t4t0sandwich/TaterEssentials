package dev.neuralnexus.taterutils.api;

import dev.neuralnexus.taterutils.modules.alert.api.AlertAPI;
import dev.neuralnexus.taterutils.modules.godmode.api.GodModeAPI;
import dev.neuralnexus.taterutils.modules.home.api.HomeAPI;
import dev.neuralnexus.taterutils.modules.mclogs.api.MCLogsAPI;
import dev.neuralnexus.taterutils.modules.orewatcher.api.OreWatcherAPI;
import dev.neuralnexus.taterutils.modules.spawn.api.SpawnAPI;
import dev.neuralnexus.taterutils.modules.tpa.api.TpaAPI;
import dev.neuralnexus.taterutils.modules.warp.api.WarpAPI;

/** API wrapper class */
public class TaterUtilsAPI {
    private final AlertAPI alertAPI;
    private final GodModeAPI godModeAPI;
    private final HomeAPI homeAPI;
    private final MCLogsAPI mclogsAPI;
    private final OreWatcherAPI oreWatcherAPI;
    private final SpawnAPI spawnAPI;
    private final TpaAPI tpaAPI;
    private final WarpAPI warpAPI;

    public TaterUtilsAPI() {
        this.alertAPI = new AlertAPI();
        this.godModeAPI = new GodModeAPI();
        this.homeAPI = new HomeAPI();
        this.mclogsAPI = new MCLogsAPI();
        this.oreWatcherAPI = new OreWatcherAPI();
        this.spawnAPI = new SpawnAPI();
        this.tpaAPI = new TpaAPI();
        this.warpAPI = new WarpAPI();
    }

    /**
     * Get the alert API.
     *
     * @return The alert API.
     */
    public AlertAPI alertAPI() {
        return alertAPI;
    }

    /**
     * Get the god mode API.
     *
     * @return The god mode API.
     */
    public GodModeAPI godModeAPI() {
        return godModeAPI;
    }

    /**
     * Get the home API.
     *
     * @return The home API.
     */
    public HomeAPI homeAPI() {
        return homeAPI;
    }

    /**
     * Get the MCLogs API.
     *
     * @return The MCLogs API.
     */
    public MCLogsAPI mcLogsAPI() {
        return mclogsAPI;
    }

    /**
     * Get the ore watcher API.
     *
     * @return The ore watcher API.
     */
    public OreWatcherAPI oreWatcherAPI() {
        return oreWatcherAPI;
    }

    /**
     * Get the spawn API.
     *
     * @return The spawn API.
     */
    public SpawnAPI spawnAPI() {
        return spawnAPI;
    }

    /**
     * Get the tpa API.
     *
     * @return The tpa API.
     */
    public TpaAPI tpaAPI() {
        return tpaAPI;
    }

    /**
     * Get the warp API.
     *
     * @return The warp API.
     */
    public WarpAPI warpAPI() {
        return warpAPI;
    }
}
