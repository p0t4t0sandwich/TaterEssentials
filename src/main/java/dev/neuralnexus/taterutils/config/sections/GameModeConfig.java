package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Game mode configuration. */
@ConfigSerializable
public class GameModeConfig {
    @Setting private String noPermission;
    @Setting private String changedSelf;
    @Setting private String notPlayer;
    @Setting private String changedOther;
    @Setting private String playerNotFound;
    @Setting private String invalidGameMode;
    @Setting private String noPermissionGamemode;

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
