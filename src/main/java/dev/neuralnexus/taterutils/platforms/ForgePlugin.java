/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterutils.TaterUtils;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TaterUtils.PROJECT_ID,
        modid = TaterUtils.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgePlugin {
    public ForgePlugin() {
        TaterUtils.instance().onEnable();
    }
}
