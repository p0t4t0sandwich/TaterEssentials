/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterutils.TaterUtils;

import org.spongepowered.api.plugin.Plugin;

/** Sponge entry point. */
@Plugin(
        id = TaterUtils.PROJECT_ID,
        name = TaterUtils.PROJECT_NAME,
        version = TaterUtils.PROJECT_VERSION,
        description = TaterUtils.PROJECT_DESCRIPTION)
public class Sponge7Plugin {
    @Inject
    public Sponge7Plugin() {
        TaterUtils.instance().start();
    }
}
