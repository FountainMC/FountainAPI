package org.fountainmc.api.world;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.*;

@Nonnull
public final class Block {
    private final World world;
    private final int x, y, z;

    public Block(World world, int x, int y, int z) {
        this.world = checkNotNull(world, "Null world");
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public Block withX(int x) {
        return new Block(world, x, y, z);
    }

    public int getY() {
        return y;
    }

    public Block withY(int y) {
        return new Block(world, x, y, z);
    }

    public int getZ() {
        return z;
    }

    public Block withZ(int z) {
        return new Block(world, x, y, z);
    }

    public World getWorld() {
        return world;
    }

    public Block withWorld(World world) {
        return new Block(checkNotNull(world, "Null world"), x, y, z);
    }

    public Location toLocation() {
        return new Location(world, x, y, z);
    }
}
