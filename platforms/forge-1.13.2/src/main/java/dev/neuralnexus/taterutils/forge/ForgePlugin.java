package dev.neuralnexus.taterutils.forge;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(TaterUtils.Constants.PROJECT_ID)
public class ForgePlugin implements TaterUtilsPlugin {
    public ForgePlugin() {
        pluginStart(this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME));
    }
}
