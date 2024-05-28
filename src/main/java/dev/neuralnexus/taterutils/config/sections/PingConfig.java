/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Configuration for the Ping module. */
@ConfigSerializable
public class PingConfig {
    @Setting("pingSelf")
    private String pingSelf;

    @Setting("pingOther")
    private String pingOther;

    @Setting("pingOtherDenied")
    private String pingOtherDenied;

    @Setting("specifyPlayer")
    private String specifyPlayer;

    @Setting("playerNotFound")
    private String playerNotFound;

    public String pingSelf() {
        return pingSelf;
    }

    public String pingOther() {
        return pingOther;
    }

    public String pingOtherDenied() {
        return pingOtherDenied;
    }

    public String specifyPlayer() {
        return specifyPlayer;
    }

    public String playerNotFound() {
        return playerNotFound;
    }
}
