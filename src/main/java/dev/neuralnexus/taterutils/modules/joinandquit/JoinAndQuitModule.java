package dev.neuralnexus.taterutils.modules.joinandquit;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.plugin.PluginModule;
import dev.neuralnexus.taterutils.TaterUtils;
import dev.neuralnexus.taterutils.TaterUtilsConfigOld;

/** JoinAndQuit module. */
public class JoinAndQuitModule implements PluginModule {
    private static final boolean RELOADED = false;
    private static boolean STARTED = false;

    @Override
    public String name() {
        return "JoinAndQuit";
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
                            event.setLoginMessage(
                                    Utils.substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigOld.JoinAndQuitConfig
                                                                    .getMessage("join"))
                                                    .getResult())));
            PlayerEvents.LOGOUT.register(
                    event ->
                            event.setLogoutMessage(
                                    Utils.substituteSectionSign(
                                            event.player()
                                                    .parsePlaceholders(
                                                            TaterUtilsConfigOld.JoinAndQuitConfig
                                                                    .getMessage("quit"))
                                                    .getResult())));
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
