package dev.neuralnexus.taterutils.api;

import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.utils.Position;

/** Implementation of {@link Location}. */
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
        this(
                location.world(),
                location.x(),
                location.y(),
                location.z(),
                location.yaw(),
                location.pitch());
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return x;
    }

    /** {@inheritDoc} */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /** {@inheritDoc} */
    @Override
    public double blockX() {
        return Math.floor(x);
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return y;
    }

    /** {@inheritDoc} */
    @Override
    public void setY(double y) {
        this.y = y;
    }

    /** {@inheritDoc} */
    @Override
    public double blockY() {
        return Math.floor(y);
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return z;
    }

    /** {@inheritDoc} */
    @Override
    public void setZ(double z) {
        this.z = z;
    }

    /** {@inheritDoc} */
    @Override
    public double blockZ() {
        return Math.floor(z);
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return yaw;
    }

    /** {@inheritDoc} */
    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return pitch;
    }

    /** {@inheritDoc} */
    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public Position blockPosition() {
        return new Position(blockX(), blockY(), blockZ());
    }

    /** {@inheritDoc} */
    @Override
    public String world() {
        return world;
    }

    /** {@inheritDoc} */
    @Override
    public void setWorld(String world) {
        this.world = world;
    }
}
