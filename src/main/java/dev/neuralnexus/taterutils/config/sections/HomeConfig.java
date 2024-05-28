package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Home configuration. */
@ConfigSerializable
public class HomeConfig {
    @Setting("homeNotSet")
    private String homeNotSet;

    @Setting("teleportedToHome")
    private String teleportedToHome;

    @Setting("invalidArguments")
    private String invalidArguments;

    @Setting("invalidHomeName")
    private String invalidHomeName;

    @Setting("noName")
    private String noName;

    @Setting("availableHomes")
    private String availableHomes;

    @Setting("setHome")
    private String setHome;

    @Setting("deleteHome")
    private String deleteHome;

    public String homeNotSet() {
        return homeNotSet;
    }

    public String teleportedToHome() {
        return teleportedToHome;
    }

    public String invalidArguments() {
        return invalidArguments;
    }

    public String invalidHomeName() {
        return invalidHomeName;
    }

    public String noName() {
        return noName;
    }

    public String availableHomes() {
        return availableHomes;
    }

    public String setHome() {
        return setHome;
    }

    public String deleteHome() {
        return deleteHome;
    }
}
