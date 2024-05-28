/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import dev.neuralnexus.taterutils.TaterUtils;
import org.slf4j.Logger;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

/** Sponge entry point. */
@Plugin(
        id = TaterUtils.PROJECT_ID,
        name = TaterUtils.PROJECT_NAME,
        version = TaterUtils.PROJECT_VERSION,
        description = TaterUtils.PROJECT_DESCRIPTION)
public class Sponge7Plugin {
    @Inject
    public Sponge7Plugin(PluginContainer container, Logger logger) {
        TaterUtils.instance()
                .pluginStart(
                        container, null, logger, new LoggerAdapter(TaterUtils.PROJECT_ID, logger));
    }
}
