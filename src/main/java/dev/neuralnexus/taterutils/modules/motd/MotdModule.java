package dev.neuralnexus.taterutils.modules.motd;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;

/** Motd module. */
public class MotdModule implements PluginModule {
    private static final boolean RELOADED = false;
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "Motd";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register listeners
            PlayerEvents.LOGIN.register(
                    event ->
                            event.player()
                                    .sendMessage(
                                            Utils.substituteSectionSign(
                                                    TaterUtilsConfigOld.MotdConfig.getMessage(
                                                            "motd"))));
        }

        TaterUtils.logger().info("Submodule " + name() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already stopped!");
            return;
        }
        STARTED = false;
        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
