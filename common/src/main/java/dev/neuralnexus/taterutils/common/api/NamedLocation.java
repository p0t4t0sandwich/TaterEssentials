package dev.neuralnexus.taterutils.common.api;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.lib.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Set;

/** A general named location class. */
public class NamedLocation {
    private final String name;
    private final String world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public NamedLocation(
            String name, String world, double x, double y, double z, float yaw, float pitch) {
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /** Get type */
    public static Type getType() {
        return new TypeToken<Set<NamedLocation>>() {}.getType();
    }

    /**
     * Get the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the location of the warp.
     *
     * @return The location of the warp.
     */
    public Location getLocation() {
        return new AbstractLocation(world, x, y, z, yaw, pitch);
    }
}
