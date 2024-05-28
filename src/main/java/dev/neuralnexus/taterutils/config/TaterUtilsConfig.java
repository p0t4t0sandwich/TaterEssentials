/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/Switchboard/blob/dev/LICENSE">GPL-3</a> The API is
 * Licensed under <a
 * href="https://github.com/p0t4t0sandwich/Switchboard/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterutils.config;

import dev.neuralnexus.taterlib.config.sections.ModuleConfig;
import dev.neuralnexus.taterutils.config.sections.BadSpawnsConfig;

import java.util.List;

/** A class for Switchboard configuration. */
public interface TaterUtilsConfig {
    /**
     * Get the version of the configuration.
     *
     * @return The version of the configuration.
     */
    int version();

    /**
     * Get the modules in the configuration.
     *
     * @return The modules in the configuration.
     */
    List<ModuleConfig> modules();

    /**
     * Get the configuration for bad spawns.
     *
     * @return The configuration for bad spawns.
     */
    BadSpawnsConfig badSpawns();
}
