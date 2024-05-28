/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Configuration for the Spawn module. */
@ConfigSerializable
public class SpawnConfig {
    @Setting("spawnNotSet")
    private String spawnNotSet;

    @Setting("teleportedToSpawn")
    private String teleportedToSpawn;

    @Setting("setSpawn")
    private String setSpawn;

    @Setting("deleteSpawn")
    private String deleteSpawn;

    public String spawnNotSet() {
        return spawnNotSet;
    }

    public String teleportedToSpawn() {
        return teleportedToSpawn;
    }

    public String setSpawn() {
        return setSpawn;
    }

    public String deleteSpawn() {
        return deleteSpawn;
    }
}
