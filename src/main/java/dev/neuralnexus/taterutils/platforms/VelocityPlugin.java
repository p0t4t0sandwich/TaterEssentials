/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;

import org.slf4j.Logger;

/** Velocity entry point. */
@Plugin(
        id = TaterUtils.PROJECT_ID,
        name = TaterUtils.PROJECT_NAME,
        version = TaterUtils.PROJECT_VERSION,
        authors = TaterUtils.PROJECT_AUTHORS,
        description = TaterUtils.PROJECT_DESCRIPTION,
        url = TaterUtils.PROJECT_URL,
        dependencies = {@Dependency(id = "taterlib")})
public class VelocityPlugin {
    @Inject
    public VelocityPlugin(PluginContainer plugin, ProxyServer server, Logger logger) {
        TaterUtils.instance()
                .pluginStart(
                        plugin, server, logger, new LoggerAdapter(TaterUtils.PROJECT_ID, logger));
    }
}
