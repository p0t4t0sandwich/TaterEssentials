/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.modules.badspawns.api;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterutils.config.TaterUtilsConfigLoader;

/** API for the BadSpawns module. */
public class BadSpawnsAPI {
    /** Check if the entity is banned globally. */
    public boolean isBannedGlobally(Entity entity) {
        return TaterUtilsConfigLoader.config().badSpawns().mobs().contains(entity.type());
    }

    /** Check if the entity is banned in the region. */
    public boolean canSpawn(Entity entity) {
        return TaterUtilsConfigLoader.config().badSpawns().regions().stream()
                .anyMatch(region -> region.canSpawn(entity));
    }
}
