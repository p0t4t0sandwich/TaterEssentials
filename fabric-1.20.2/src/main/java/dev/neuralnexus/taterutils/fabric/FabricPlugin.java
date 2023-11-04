package dev.neuralnexus.taterutils.fabric;

import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.common.logger.GenericLogger;
import dev.neuralnexus.taterutils.common.Constants;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.fabricmc.api.DedicatedServerModInitializer;

/**
 * Fabric entry point.
 */
public class FabricPlugin implements DedicatedServerModInitializer, TaterUtilsPlugin {
    @Override
    public void onInitializeServer() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new GenericLogger("[" + Constants.PROJECT_NAME + "] ", Constants.PROJECT_ID));
    }
}
