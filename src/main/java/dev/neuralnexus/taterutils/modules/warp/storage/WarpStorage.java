/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.warp.storage;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterutils.api.NamedLocation;

import java.util.Optional;
import java.util.Set;

/** Storage API for the warp module. */
public interface WarpStorage {
    /**
     * Get a Warp.
     *
     * @param warp The name of the warp.
     */
    Optional<NamedLocation> getWarp(String warp);

    /**
     * Set a warp.
     *
     * @param warp The name of the warp.
     * @param location The location of the warp.
     */
    void setWarp(String warp, Location location);

    /**
     * Delete a warp.
     *
     * @param warp The name of the warp.
     */
    void deleteWarp(String warp);

    /**
     * Get all warps.
     *
     * @return All warps.
     */
    Set<NamedLocation> getWarps();

    /**
     * Teleport a player to a warp.
     *
     * @param player The player.
     */
    boolean teleportWarp(Player player, String warp);
}
