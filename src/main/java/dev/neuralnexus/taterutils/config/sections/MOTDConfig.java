package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** MOTD configuration. */
@ConfigSerializable
public class MOTDConfig {
    @Setting private String message;

    public String message() {
        return message;
    }
}
