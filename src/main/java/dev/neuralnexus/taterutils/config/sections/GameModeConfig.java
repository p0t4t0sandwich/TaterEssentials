package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Game mode configuration. */
@ConfigSerializable
public class GameModeConfig {
    @Setting("noPermission")
    private String noPermission;

    @Setting("changedSelf")
    private String changedSelf;

    @Setting("notPlayer")
    private String notPlayer;

    @Setting("changedOther")
    private String changedOther;

    @Setting("playerNotFound")
    private String playerNotFound;

    @Setting("invalidGameMode")
    private String invalidGameMode;

    @Setting("noPermissionGamemode")
    private String noPermissionGamemode;

    public String noPermission() {
        return noPermission;
    }

    public String changedSelf() {
        return changedSelf;
    }

    public String notPlayer() {
        return notPlayer;
    }

    public String changedOther() {
        return changedOther;
    }

    public String playerNotFound() {
        return playerNotFound;
    }

    public String invalidGameMode() {
        return invalidGameMode;
    }

    public String noPermissionGamemode() {
        return noPermissionGamemode;
    }
}
