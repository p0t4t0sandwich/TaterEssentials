/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterUtils/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterutils.api;

import dev.neuralnexus.taterlib.api.TaterAPIProvider;
import dev.neuralnexus.taterlib.server.Server;
import dev.neuralnexus.taterlib.world.BlockPos;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

/** Implementation of {@link Location}. */
public class AbstractLocation implements Location {
    private World world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public AbstractLocation(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public AbstractLocation(String world, double x, double y, double z, float yaw, float pitch) {
        this(((Server) TaterAPIProvider.get().getServer()).world(world).get(), x, y, z, yaw, pitch);
    }

    public AbstractLocation(Location location) {
        this(
                location.world(),
                location.x(),
                location.y(),
                location.z(),
                location.yaw(),
                location.pitch());
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double blockX() {
        return Math.floor(x);
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double blockY() {
        return Math.floor(y);
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double blockZ() {
        return Math.floor(z);
    }

    @Override
    public float yaw() {
        return yaw;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public float pitch() {
        return pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public BlockPos blockPosition() {
        return new BlockPos(blockX(), blockY(), blockZ());
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }
}
