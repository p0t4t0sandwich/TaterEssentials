package dev.neuralnexus.taterutils.modules.gamemode;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.modules.gamemode.command.GameModeCommand;

/** Ping module. */
public class GameModeModule implements PluginModule {
    private static final boolean RELOADED = false;
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "GameMode";
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
            if (!TaterAPIProvider.serverType().isProxy()) {
                CommandEvents.REGISTER_COMMAND.register(
                        event -> {
                            // TODO: Refactor to use aliases once Brig aliases are patched upstream
                            // event.registerCommand(TaterUtils.plugin(), command, "gms", "gmc",
                            // "gma", "gmsp");
                            Command gamemode = new GameModeCommand();
                            Command gms = new GameModeCommand();
                            gms.setName("gms");
                            Command gmc = new GameModeCommand();
                            gmc.setName("gmc");
                            Command gma = new GameModeCommand();
                            gma.setName("gma");
                            Command gmsp = new GameModeCommand();
                            gmsp.setName("gmsp");

                            event.registerCommand(TaterUtils.plugin(), gamemode, "gamemode");
                            event.registerCommand(TaterUtils.plugin(), gms);
                            event.registerCommand(TaterUtils.plugin(), gmc);
                            event.registerCommand(TaterUtils.plugin(), gma);
                            event.registerCommand(TaterUtils.plugin(), gmsp);
                        });
            }
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

        // Remove references to objects

        TaterUtils.logger().info("Submodule " + name() + " has been stopped!");
    }
}
