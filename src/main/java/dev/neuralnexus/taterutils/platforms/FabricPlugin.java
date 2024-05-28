/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import dev.neuralnexus.taterutils.TaterUtils;
import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricPlugin implements ModInitializer {
    public FabricPlugin() {
        TaterUtils.instance()
                .pluginStart(this, null, null, new LoggerAdapter(TaterUtils.PROJECT_ID));
    }

    @Override
    public void onInitialize() {}
}
