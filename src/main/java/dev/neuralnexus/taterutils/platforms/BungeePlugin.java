/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;

import dev.neuralnexus.taterutils.TaterUtils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/** Bungee entry point. */
public class BungeePlugin extends Plugin {
    public BungeePlugin() {
        TaterUtils.instance()
                .pluginStart(
                        this,
                        ProxyServer.getInstance(),
                        getLogger(),
                        new LoggerAdapter(TaterUtils.PROJECT_ID, getLogger()));
    }
}
