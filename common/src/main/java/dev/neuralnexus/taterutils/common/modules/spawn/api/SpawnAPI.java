package dev.neuralnexus.taterutils.common.modules.spawn.api;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.storage.Database;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.storage.spawn.FSSpawnStorage;
import dev.neuralnexus.taterutils.common.storage.spawn.SpawnStorage;

import java.util.Optional;

public class SpawnAPI {
    private final SpawnStorage database;

    public SpawnAPI() {
        this.database = new FSSpawnStorage(new Database.DatabaseConfig(TaterUtils.Constants.PROJECT_NAME, 0, "warpData", "", ""));
    }

    /**
     * Get the spawn.
     * @return The spawn.
     */
    public Optional<Location> getSpawn() {
        return this.database.getSpawn();
    }

    /**
     * Sets the spawn.
     * @param location The location of the spawn.
     */
    public void setSpawn(Location location) {
        this.database.setSpawn(location);
    }

    /**
     * Delete the spawn.
     */
    public void deleteSpawn() {
        this.database.deleteSpawn();
    }

    /**
     * Teleport a player to the spawn.
     * @param player The player.
     */
    public boolean teleportSpawn(Player player) {
        return this.database.teleportSpawn(player);
    }
}
