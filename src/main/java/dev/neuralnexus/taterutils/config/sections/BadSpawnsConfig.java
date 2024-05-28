/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.config.sections;

import dev.neuralnexus.taterutils.modules.badspawns.api.Region;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

/** A class for bad spawns configuration. */
@ConfigSerializable
public class BadSpawnsConfig {
    @Setting List<String> mobs;
    @Setting List<Region> regions;

    public List<String> mobs() {
        return mobs;
    }

    public List<Region> regions() {
        return regions;
    }
}
