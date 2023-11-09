package dev.neuralnexus.taterutils.common.storage;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.api.modules.HomeAPI;
import dev.neuralnexus.taterutils.common.api.modules.WarpAPI;

import java.util.Optional;
import java.util.Set;
/**
 * Storage API for the warp module.
 */
public interface WarpStorage {
    /**
     * Get a Warp.
     * @param warp The name of the warp.
     */
    Optional<WarpAPI.WarpLocation> getWarp(String warp);


    /**
     * Set a warp.
     * @param player The player.
     * @param warp The name of the warp.
     * @param location The location of the warp.
     */
    void setWarp(Player player, String warp, Location location);

    /**
     * Delete a warp.
     * @param warp The name of the warp.
     */
    void deleteWarp(String warp);

    /**
     * Get all of the warps.

     */

    Set<WarpAPI.WarpLocation> getWarps();

    /**
     * Teleport a player to a warp.
     * @param player The player.
     */
    boolean teleportWarp(Player player, String warp);
}

