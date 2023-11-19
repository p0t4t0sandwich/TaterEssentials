package dev.neuralnexus.taterutils.common.modules.send.api;

import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.common.player.ProxyPlayer;

/** API for the Send module. */
public class SendAPI {
    public SendAPI() {}

    /**
     * Send a player to the specified server.
     *
     * @param player The player.
     * @param server The server.
     */
    public void sendPlayer(Player player, String server) {
        if (player instanceof ProxyPlayer) {
            ((ProxyPlayer) player).connect(server);
        }
    }
}
