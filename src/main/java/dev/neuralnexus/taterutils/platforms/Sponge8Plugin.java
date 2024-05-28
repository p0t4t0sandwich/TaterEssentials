/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import dev.neuralnexus.taterutils.TaterUtils;
import org.apache.logging.log4j.Logger;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

/** Sponge entry point. */
@Plugin(TaterUtils.PROJECT_ID)
public class Sponge8Plugin {
    @Inject
    public Sponge8Plugin(PluginContainer container, Logger logger) {
        TaterUtils.instance()
                .pluginStart(
                        container, null, logger, new LoggerAdapter(TaterUtils.PROJECT_ID, logger));
    }
}
