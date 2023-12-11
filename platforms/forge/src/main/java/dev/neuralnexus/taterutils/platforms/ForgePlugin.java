package dev.neuralnexus.taterutils.platforms;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TaterUtils.Constants.PROJECT_ID,
        modid = TaterUtils.Constants.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgePlugin implements TaterUtilsPlugin {
    public ForgePlugin() {
        pluginStart(this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME));
    }
}
