package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** God mode configuration. */
@ConfigSerializable
public class GodModeConfig {
    @Setting("toggleSelf")
    private String toggleSelf;

    @Setting("toggleOther")
    private String toggleOther;

    @Setting("specifyPlayer")
    private String specifyPlayer;

    @Setting("playerNotFound")
    private String playerNotFound;

    @Setting("godmodeOtherDenied")
    private String godmodeOtherDenied;

    public String toggleSelf() {
        return toggleSelf;
    }

    public String toggleOther() {
        return toggleOther;
    }

    public String specifyPlayer() {
        return specifyPlayer;
    }

    public String playerNotFound() {
        return playerNotFound;
    }

    public String godmodeOtherDenied() {
        return godmodeOtherDenied;
    }
}
