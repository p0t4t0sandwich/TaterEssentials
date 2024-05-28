package dev.neuralnexus.taterutils.modules.badspawns.api;

import dev.neuralnexus.taterlib.entity.Entity;
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
