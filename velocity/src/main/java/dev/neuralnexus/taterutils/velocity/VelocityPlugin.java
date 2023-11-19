package dev.neuralnexus.taterutils.velocity;

import com.velocitypowered.api.plugin.Dependency;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.neuralnexus.taterlib.common.event.api.ServerEvents;
import dev.neuralnexus.taterlib.velocity.logger.VelocityLogger;
import dev.neuralnexus.taterutils.common.TaterUtils;
import dev.neuralnexus.taterutils.common.TaterUtilsPlugin;
import org.slf4j.Logger;

/** Velocity entry point. */
@Plugin(
        id = TaterUtils.Constants.PROJECT_ID,
        name = TaterUtils.Constants.PROJECT_NAME,
        version = TaterUtils.Constants.PROJECT_VERSION,
        authors = TaterUtils.Constants.PROJECT_AUTHORS,
        description = TaterUtils.Constants.PROJECT_DESCRIPTION,
        url = TaterUtils.Constants.PROJECT_URL,
        dependencies = {
            @Dependency(id = "taterlib"),
            @Dependency(id = "luckperms", optional = true)
        })
public class VelocityPlugin implements TaterUtilsPlugin {
    @Inject
    public VelocityPlugin(ProxyServer server, Logger logger) {
        ServerEvents.STOPPED.register(event -> pluginStop());
        pluginStart(server, new VelocityLogger(logger));
    }
}
