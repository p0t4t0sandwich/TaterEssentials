/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

/** NeoForge entry point. */
@Mod(TaterUtils.PROJECT_ID)
public class NeoForgePlugin {
    public NeoForgePlugin() {
        TaterUtils.instance()
                .pluginStart(
                        this,
                        ServerLifecycleHooks.getCurrentServer(),
                        LogUtils.getLogger(),
                        new LoggerAdapter(TaterUtils.PROJECT_ID, LogUtils.getLogger()));
    }
}
