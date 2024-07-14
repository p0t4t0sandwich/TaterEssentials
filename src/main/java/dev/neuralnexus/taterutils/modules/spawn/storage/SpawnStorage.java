/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.spawn.storage;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.world.Location;

import java.util.Optional;

/** Storage API for the spawn module. */
public interface SpawnStorage {
    /** Get the spawn. */
    Optional<Location> getSpawn();

    /**
     * Set the spawn.
     *
     * @param location The location of the spawn.
     */
    void setSpawn(Location location);

    /** Delete the spawn. */
    void deleteSpawn();

    /**
     * Teleport a player to the spawn.
     *
     * @param player The player.
     */
    boolean teleportSpawn(Player player);
}
