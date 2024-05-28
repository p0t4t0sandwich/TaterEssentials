/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.warp.api;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.storage.databases.Database;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.api.NamedLocation;
import dev.neuralnexus.taterutils.modules.warp.storage.FSWarpStorage;
import dev.neuralnexus.taterutils.modules.warp.storage.WarpStorage;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class WarpAPI {
    private final WarpStorage database;

    public WarpAPI() {
        this.database =
                new FSWarpStorage(
                        new Database.DatabaseConfig(
                                TaterUtils.PROJECT_NAME, 0, "warpData", "", ""));
    }

    /**
     * Get a warp.
     *
     * @param warp The name of the warp.
     */
    public Optional<NamedLocation> getWarp(String warp) {
        return this.database.getWarp(warp);
    }

    /**
     * Set a Warp.
     *
     * @param warp The name of the warp.
     * @param location The location of the warp.
     */
    public void setWarp(String warp, Location location) {
        this.database.setWarp(warp, location);
    }

    /**
     * Delete a Warp.
     *
     * @param warp The name of the warp.
     */
    public void deleteWarp(String warp) {
        this.database.deleteWarp(warp);
    }

    /** Get all Warps. */
    public Set<NamedLocation> getWarps() {
        return this.database.getWarps();
    }

    /**
     * Teleport a player to a Warp.
     *
     * @param player The player.
     * @param warp the warp name.
     */
    public boolean teleportWarp(Player player, String warp) {
        return this.database.teleportWarp(player, warp);
    }

    /**
     * Get invalid warp names.
     *
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
