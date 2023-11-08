package dev.neuralnexus.taterutils.forge;

import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.logger.GenericLogger;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge entry point.
 */
@Mod(TaterUtils.Constants.PROJECT_ID)
public class ForgePlugin implements TaterUtilsPlugin {
    public ForgePlugin() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new GenericLogger("[" + TaterUtils.Constants.PROJECT_NAME + "] ", TaterUtils.Constants.PROJECT_ID));
    }
}
