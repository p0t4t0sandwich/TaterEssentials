package dev.neuralnexus.taterutils.common.modules.spawn.storage;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.utils.Location;

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
