package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** JoinAndQuit configuration. */
@ConfigSerializable
public class JoinAndQuitConfig {
    @Setting private String join;
    @Setting private String quit;

    public String join() {
        return join;
    }

    public String quit() {
        return quit;
    }
}
