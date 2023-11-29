package dev.neuralnexus.taterutils.fabric;

import dev.neuralnexus.taterlib.logger.GenericLogger;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricPlugin implements ModInitializer, TaterUtilsPlugin {
    public FabricPlugin() {
        pluginStart(
                this,
                new GenericLogger(
                        "[" + TaterUtils.Constants.PROJECT_NAME + "] ",
                        TaterUtils.Constants.PROJECT_ID));
    }

    @Override
    public void onInitialize() {}
}
