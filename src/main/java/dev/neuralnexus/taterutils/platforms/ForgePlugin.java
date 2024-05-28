/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;

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
        TaterUtils.instance()
                .pluginStart(this, null, null, new LoggerAdapter(TaterUtils.PROJECT_ID));
    }
}
