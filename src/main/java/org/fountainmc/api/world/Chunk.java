package org.fountainmc.api.world;

import org.fountainmc.api.world.block.BlockState;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public interface Chunk {

    public int getX();

    public int getZ();

    public World getWorld();

    public default BlockState getBlockAt(BlockPosition pos) {
        checkNotNull(pos, "Null position");
        checkArgument(pos.getWorld() == this.getWorld(), "Position's world %s doesn't equal chunk's world %s", pos.getWorld().getName(),
                this.getWorld().getName());
        return getBlockAt(pos.getX(), pos.getY(), pos.getZ());
    }

    public BlockState getBlockAt(int x, int y, int z);

}
