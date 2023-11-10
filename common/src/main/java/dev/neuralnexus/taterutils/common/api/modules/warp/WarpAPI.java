package dev.neuralnexus.taterutils.common.api.modules.warp;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.api.modules.NamedLocation;
import dev.neuralnexus.taterutils.common.storage.warp.FSWarpStorage;
import dev.neuralnexus.taterutils.common.storage.warp.WarpStorage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WarpAPI {
    private final WarpStorage database;

    public WarpAPI() {
        this.database = new FSWarpStorage(new Database.DatabaseConfig(TaterUtils.Constants.PROJECT_NAME, 0, "warpData", "", ""));
    }

    /**
     * Get a warp.
     * @param warp The name of the warp.
     */
    public Optional<NamedLocation> getWarp(String warp) {
        return this.database.getWarp(warp);
    }

    /**
     * Set a Warp.
     * @param warp The name of the warp.
     * @param location The location of the warp.
     */
    public void setWarp(String warp, Location location) {
        this.database.setWarp(warp, location);
    }

    /**
     * Delete a Warp.
     * @param warp  The name of the warp.
     */
    public void deleteWarp(String warp) {
        this.database.deleteWarp(warp);
    }

    /**
     * Get all Warps.
     */
    public Set<NamedLocation> getWarps() {
        return this.database.getWarps();
    }

    /**
     * Teleport a player to a Warp.
     * @param player The player.
     * @param warp the warp name.
     */
    public boolean teleportWarp(Player player, String warp) {
        return this.database.teleportWarp(player, warp);
    }

    /**
     * Get invalid warp names.
     * @return The invalid warp names.
     */
    public Set<String> getInvalidWarpNames() {
        Set<String> invalidWarps = new HashSet<>();
        invalidWarps.add("add");
        invalidWarps.add("create");
        invalidWarps.add("set");
        invalidWarps.add("rm");
        invalidWarps.add("remove");
        invalidWarps.add("del");
        invalidWarps.add("delete");
        invalidWarps.add("list");
        return invalidWarps;
    }
}
