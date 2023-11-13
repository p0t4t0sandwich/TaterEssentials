package dev.neuralnexus.taterutils.common.modules.alert.api;

import dev.neuralnexus.taterlib.common.api.TaterAPIProvider;

/**
 * API for the Send module.
 */
public class AlertAPI {
    public AlertAPI() {}

    /**
     * Broadcast a message to the server.
     * @param message The message to broadcast.
     */
    public void broadcast(String message) {
        // TODO: Replace when Server.broadcastMessage is implemented.
        TaterAPIProvider.get().getServer().getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }
}
