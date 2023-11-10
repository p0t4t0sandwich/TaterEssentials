package dev.neuralnexus.taterutils.common.api.modules;

import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.common.utils.Position;

/**
 * Implementation of {@link Location}.
 */
public class AbstractLocation implements Location {
    private String world;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public AbstractLocation(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public AbstractLocation(Location location) {
        this(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockX() {
        return Math.floor(x);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockY() {
        return Math.floor(y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBlockZ() {
        return Math.floor(z);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYaw() {
        return yaw;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getPitch() {
        return pitch;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getBlockPosition() {
        return new Position(getBlockX(), getBlockY(), getBlockZ());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWorld() {
        return world;
    }
}
