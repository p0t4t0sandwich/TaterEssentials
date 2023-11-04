package dev.neuralnexus.taterutils.forge;

import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.logger.GenericLogger;
import dev.neuralnexus.taterutils.common.Constants;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge entry point.
 */
@Mod(Constants.PROJECT_ID)
public class ForgePlugin implements TaterUtilsPlugin {
    public ForgePlugin() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new GenericLogger("[" + Constants.PROJECT_NAME + "] ", Constants.PROJECT_ID));
    }
}
