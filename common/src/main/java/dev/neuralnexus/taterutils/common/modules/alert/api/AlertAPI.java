package dev.neuralnexus.taterutils.common.modules.alert.api;

import dev.neuralnexus.taterlib.Utils;
import dev.neuralnexus.taterlib.api.TaterAPIProvider;

/** API for the Send module. */
public class AlertAPI {
    public AlertAPI() {}

    /**
     * Broadcast a message to the server.
     *
     * @param message The message to broadcast.
     */
    public void broadcast(String message) {
        TaterAPIProvider.get().getServer().broadcastMessage(Utils.substituteSectionSign(message));
    }
}
