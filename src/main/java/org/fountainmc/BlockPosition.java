package org.fountainmc;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.*;

@Nonnull
public final class BlockPosition {
    private final World world;
    private final int x, y, z;

    public BlockPosition(World world, int x, int y, int z) {
        this.world = checkNotNull(world, "Null world");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public BlockPosition withX(int x) {
        return new BlockPosition(world, x, y, z);
    }

    public int getY() {
        return y;
    }

    public BlockPosition withY(int y) {
        return new BlockPosition(world, x, y, z);
    }

    public int getZ() {
        return z;
    }

    public BlockPosition withZ(int z) {
        return new BlockPosition(world, x, y, z);
    }

    public World getWorld() {
        return world;
    }

    public BlockPosition withWorld(World world) {
        return new BlockPosition(checkNotNull(world, "Null world"), x, y, z);
    }

    public Location toLocation() {
        return new Location(world, x, y, z);
    }
}
