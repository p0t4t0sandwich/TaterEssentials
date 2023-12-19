package dev.neuralnexus.taterutils.modules.gamemode;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.Module;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.gamemode.command.GameModeCommand;

/** Ping module. */
public class GameModeModule implements Module {
    private static boolean STARTED = false;
    private static boolean RELOADED = false;

    @Override
    public String getName() {
        return "Ping";
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
            if (!TaterAPIProvider.serverType().isProxy()) {
                CommandEvents.REGISTER_COMMAND.register(
                        event ->
                                event.registerCommand(
                                        TaterUtils.getPlugin(),
                                        new GameModeCommand(),
                                        "gm",
                                        "gms",
                                        "gmc",
                                        "gma",
                                        "gmsp"));
            }
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
