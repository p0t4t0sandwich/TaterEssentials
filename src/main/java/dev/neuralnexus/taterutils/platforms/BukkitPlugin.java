/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
 */
package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin {
    public BukkitPlugin() {
        TaterUtils.instance()
                .pluginStart(
                        this,
                        getServer(),
                        getLogger(),
                        new LoggerAdapter(TaterUtils.PROJECT_ID, getLogger()));
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
