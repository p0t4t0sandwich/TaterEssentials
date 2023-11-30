package dev.neuralnexus.taterutils.fabric;

import dev.neuralnexus.taterlib.logger.LoggerAdapter;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsPlugin;

import net.fabricmc.api.ModInitializer;

/** Fabric entry point. */
public class FabricPlugin implements ModInitializer, TaterUtilsPlugin {
    public FabricPlugin() {
        pluginStart(this, new LoggerAdapter(TaterUtils.Constants.PROJECT_NAME));
    }

    @Override
    public void onInitialize() {}
}
