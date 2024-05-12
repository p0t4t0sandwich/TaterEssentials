package dev.neuralnexus.taterutils.modules.warp;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.warp.command.DelWarpCommand;
import dev.neuralnexus.taterutils.modules.warp.command.SetWarpCommand;
import dev.neuralnexus.taterutils.modules.warp.command.WarpCommand;

/** Warp module. */
public class WarpModule implements PluginModule {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String name() {
        return "Warp";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.logger().info("Submodule " + name() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        if (!TaterAPIProvider.serverType().isProxy()) {
                            event.registerCommand(TaterUtils.plugin(), new WarpCommand(), "warps");
                            event.registerCommand(
                                    TaterUtils.plugin(),
                                    new SetWarpCommand(),
                                    "addwarp",
                                    "createwarp");
                            event.registerCommand(
                                    TaterUtils.plugin(),
                                    new DelWarpCommand(),
                                    "deletewarp",
                                    "removewarp",
                                    "rmwarp");
                        }
                    }));
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
        RELOADED = true;

        // Remove references to objects

        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
