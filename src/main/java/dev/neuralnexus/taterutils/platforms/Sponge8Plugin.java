/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterutils.TaterUtils;

import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterUtils.PROJECT_ID)
public class Sponge8Plugin {
    @Inject
    public Sponge8Plugin() {
        TaterUtils.instance().onEnable();
    }
}
