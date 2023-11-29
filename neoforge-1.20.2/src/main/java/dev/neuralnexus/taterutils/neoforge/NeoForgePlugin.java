package dev.neuralnexus.taterutils.neoforge;

import dev.neuralnexus.taterlib.logger.GenericLogger;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.neoforged.fml.common.Mod;

/** NeoForge entry point. */
@Mod(TaterUtils.Constants.PROJECT_ID)
public class NeoForgePlugin implements TaterUtilsPlugin {
    public NeoForgePlugin() {
        pluginStart(
                this,
                new GenericLogger(
                        "[" + TaterUtils.Constants.PROJECT_NAME + "] ",
                        TaterUtils.Constants.PROJECT_ID));
    }
}
