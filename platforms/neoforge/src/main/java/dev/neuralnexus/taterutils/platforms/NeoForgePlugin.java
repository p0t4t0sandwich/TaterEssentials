package dev.neuralnexus.taterutils.platforms;

import com.mojang.logging.LogUtils;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.neoforged.fml.common.Mod;

/** NeoForge entry point. */
@Mod(TaterUtils.Constants.PROJECT_ID)
public class NeoForgePlugin implements TaterUtilsPlugin {
    public NeoForgePlugin() {
        pluginStart(
                this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME, LogUtils.getLogger()));
    }
}
