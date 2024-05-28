package dev.neuralnexus.taterutils.config.sections;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

/** Configuration for the OreWatcher module. */
@ConfigSerializable
public class OreWatcherConfig {
    @Setting("alertThreshold")
    private int alertThreshold;

    @Setting("cancelMinedOverThreshold")
    private boolean cancelMinedOverThreshold;

    @Setting("adminAlertEnabled")
    private boolean adminAlertEnabled;

    @Setting("playerAlertEnabled")
    private boolean playerAlertEnabled;

    @Setting("adminAlertMessage")
    private String adminAlertMessage;

    @Setting("playerAlertMessage")
    private String playerAlertMessage;

    public int alertThreshold() {
        return alertThreshold;
    }

    public boolean cancelMinedOverThreshold() {
        return cancelMinedOverThreshold;
    }

    public boolean adminAlertEnabled() {
        return adminAlertEnabled;
    }

    public boolean playerAlertEnabled() {
        return playerAlertEnabled;
    }

    public String adminAlertMessage() {
        return adminAlertMessage;
    }

    public String playerAlertMessage() {
        return playerAlertMessage;
    }
}
