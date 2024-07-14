/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import cpw.mods.fml.common.Mod;

import dev.neuralnexus.taterutils.TaterUtils;

/** Legacy Forge entry point. */
@Mod(
        modid = TaterUtils.PROJECT_ID,
        name = TaterUtils.PROJECT_NAME,
        useMetadata = true,
        acceptableRemoteVersions = "*",
        bukkitPlugin = TaterUtils.PROJECT_NAME)
public class LegacyForgePlugin {
    public LegacyForgePlugin() {
        TaterUtils.instance().start();
    }
}
