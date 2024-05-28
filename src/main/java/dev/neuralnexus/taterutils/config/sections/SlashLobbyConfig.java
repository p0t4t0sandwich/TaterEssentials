package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Configuration for the SlashLobby module. */
@ConfigSerializable
public class SlashLobbyConfig {
    @Setting("lobbyNames")
    private String[] lobbyNames;

    @Setting("connectedToLobby")
    private String connectedToLobby;

    public String[] lobbyNames() {
        return lobbyNames;
    }

    public String connectedToLobby() {
        return connectedToLobby;
    }
}
