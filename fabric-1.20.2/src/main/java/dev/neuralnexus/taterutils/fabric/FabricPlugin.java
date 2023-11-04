package dev.neuralnexus.taterutils.fabric;

import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.fabric.logger.FabricLogger;
import dev.neuralnexus.taterutils.common.Constants;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import net.fabricmc.api.DedicatedServerModInitializer;
import org.slf4j.LoggerFactory;

/**
 * Fabric entry point.
 */
public class FabricPlugin implements DedicatedServerModInitializer, TaterUtilsPlugin {
    @Override
    public void onInitializeServer() {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(this, new FabricLogger("[" + Constants.PROJECT_NAME + "] ", LoggerFactory.getLogger(Constants.PROJECT_ID)));
    }
}
