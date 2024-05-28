/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

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
