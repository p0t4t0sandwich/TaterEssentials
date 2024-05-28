/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.api;

import com.google.gson.reflect.TypeToken;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import java.lang.reflect.Type;
import java.util.Set;

/** A general named location class. */
public class NamedLocation {
    private final String name;
    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public NamedLocation(
            String name, World world, double x, double y, double z, float yaw, float pitch) {
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public NamedLocation(
            String name, String world, double x, double y, double z, float yaw, float pitch) {
        this(
                name,
                ((Server) TaterAPIProvider.get().getServer()).world(world).get(),
                x,
                y,
                z,
                yaw,
                pitch);
    }

    public NamedLocation(String name, Location location) {
        this(
                name,
                location.world(),
                location.x(),
                location.y(),
                location.z(),
                location.yaw(),
                location.pitch());
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
