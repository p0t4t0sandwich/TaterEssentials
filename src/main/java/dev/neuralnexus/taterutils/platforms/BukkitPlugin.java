/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterutils.TaterUtils;

import org.bukkit.plugin.java.JavaPlugin;

/** Bukkit entry point. */
public class BukkitPlugin extends JavaPlugin {
    public BukkitPlugin() {
        TaterUtils.instance().onEnable();
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}
}
