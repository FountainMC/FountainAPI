package org.fountainmc.api.world;

import javax.annotation.Nonnull;

import org.fountainmc.api.Direction;
import org.fountainmc.api.world.block.BlockState;

import static com.google.common.base.Preconditions.checkNotNull;

@Nonnull
public final class BlockPosition {

    private final World world;
    private final int x;
    private final int y;
    private final int z;

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

    public BlockPosition withCoordinates(int x, int y, int z) {
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

    public BlockPosition offset(Direction direction) {
        return offset(direction, 1);
    }

    public BlockPosition offset(Direction direction, int amount) {
        int x = this.x;
        int y = this.y;
        int z = this.z;
        switch (checkNotNull(direction, "Null direction")) {
            case NORTH:
                x -= amount;
                break;
            case SOUTH:
                x += amount;
                break;
            case EAST:
                z += amount;
                break;
            case WEST:
                z -= amount;
                break;
            case UP:
                y += amount;
                break;
            case DOWN:
                y -= amount;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        return withCoordinates(x, y, z);
    }

    public void setState(BlockState state) {
        world.setBlock(this, state);
    }

}
