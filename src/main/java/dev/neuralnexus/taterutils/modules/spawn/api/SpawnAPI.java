package dev.neuralnexus.taterutils.modules.spawn.api;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.storage.databases.Database;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.spawn.storage.FSSpawnStorage;
import dev.neuralnexus.taterutils.modules.spawn.storage.SpawnStorage;

import java.util.Optional;

/** API for the Spawn module. */
public class SpawnAPI {
    private final SpawnStorage database;

    public SpawnAPI() {
        this.database =
                new FSSpawnStorage(
                        new Database.DatabaseConfig(
                                TaterUtils.PROJECT_NAME, 0, "spawnData", "", ""));
    }

    /**
     * Get the spawn.
     *
     * @return The spawn.
     */
    public Optional<Location> getSpawn() {
        return this.database.getSpawn();
    }

    /**
     * Sets the spawn.
     *
     * @param location The location of the spawn.
     */
    public void setSpawn(Location location) {
        this.database.setSpawn(location);
    }

    /** Delete the spawn. */
    public void deleteSpawn() {
        this.database.deleteSpawn();
    }

    /**
     * Teleport a player to the spawn.
     *
     * @param player The player.
     */
    public boolean teleportSpawn(Player player) {
        return this.database.teleportSpawn(player);
    }
}
