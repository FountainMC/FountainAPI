package org.fountainmc.api.world;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Nonnull
public final class Location {

    private final World world;
    private final double x, y, z;

    public Location(World world, double x, double y, double z) {
        this.world = checkNotNull(world, "Null world");
        checkArgument(!Double.isInfinite(x), "Infinite x");
        checkArgument(!Double.isInfinite(y), "Infinite y");
        checkArgument(!Double.isInfinite(z), "Infinite z");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location add(double x, double y, double z) {
        return new Location(world, this.x + x, this.y + y, this.z + z);
    }

    public double getX() {
        return x;
    }

    public Location withX(double x) {
        return new Location(world, x, y, z);
    }

    public double getY() {
        return y;
    }

    public Location withY(double y) {
        return new Location(world, x, y, z);
    }

    public double getZ() {
        return z;
    }

    public Location withZ(double z) {
        return new Location(world, x, y, z);
    }

    public World getWorld() {
        return world;
    }

    public Location withWorld(World world) {
        return new Location(checkNotNull(world, "Null world"), x, y, z);
    }

    public BlockPosition getBlock() {
        return new BlockPosition(world, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

}
