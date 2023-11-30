package dev.neuralnexus.taterutils.modules.warp;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.Module;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.warp.command.DelWarpCommand;
import dev.neuralnexus.taterutils.modules.warp.command.SetWarpCommand;
import dev.neuralnexus.taterutils.modules.warp.command.WarpCommand;

/** Warp module. */
public class WarpModule implements Module {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String getName() {
        return "Warp";
    }

    @Override
    public void start() {
        if (STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has already started!");
            return;
        }
        STARTED = true;

        if (!RELOADED) {
            // Register commands
            CommandEvents.REGISTER_COMMAND.register(
                    (event -> {
                        if (!TaterAPIProvider.get().serverType().isProxy()) {
                            event.registerCommand(
                                    TaterUtils.getPlugin(), new WarpCommand(), "warps");
                            event.registerCommand(
                                    TaterUtils.getPlugin(),
                                    new SetWarpCommand(),
                                    "addwarp",
                                    "createwarp");
                            event.registerCommand(
                                    TaterUtils.getPlugin(),
                                    new DelWarpCommand(),
                                    "deletewarp",
                                    "removewarp",
                                    "rmwarp");
                        }
                    }));
        }

        TaterUtils.getLogger().info("Submodule " + getName() + " has been started!");
    }

    @Override
    public void stop() {
        if (!STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has already stopped!");
            return;
        }
        STARTED = false;
        RELOADED = true;

        // Remove references to objects

        TaterUtils.getLogger().info("Submodule " + getName() + " has been stopped!");
    }

    @Override
    public void reload() {
        if (!STARTED) {
            TaterUtils.getLogger().info("Submodule " + getName() + " has not been started!");
            return;
        }
        RELOADED = true;

        // Stop
        stop();

        // Start
        start();

        TaterUtils.getLogger().info("Submodule " + getName() + " has been reloaded!");
    }
}
