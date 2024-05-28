/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca This project is Licensed under All Rights
 * Reserved
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
