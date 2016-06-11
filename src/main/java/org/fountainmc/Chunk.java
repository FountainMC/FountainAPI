package org.fountainmc;

import org.fountainmc.block.Block;

import static com.google.common.base.Preconditions.*;

public interface Chunk {

    public int getX();

    public int getZ();

    public World getWorld();

    public default Block getBlockAt(BlockPosition pos) {
        checkNotNull(pos, "Null position");
        checkArgument(pos.getWorld() == this.getWorld(), "Position's world %s doesn't equal chunk's world %s", pos.getWorld().getName(), this.getWorld().getName());
        return getBlockAt(pos.getX(), pos.getY(), pos.getZ());
    }

    public Block getBlockAt(int x, int y, int z);
}
