package org.fountainmc;

import org.fountainmc.block.Block;

import static com.google.common.base.Preconditions.*;

public interface World {

    public String getName();

    public Chunk getChunk(int x, int y);

    public default Chunk getChunk(BlockPosition pos) {
        checkNotNull(pos, "Null position");
        checkArgument(pos.getWorld() == this, "Position's world %s doesn't equal chunk's world %s", pos.getWorld().getName(), this.getName());
        return getChunk(pos.getX() >> 4, pos.getY() >> 4);
    }

    public default Block getBlockAt(BlockPosition pos) {
        checkNotNull(pos, "Null position");
        checkArgument(pos.getWorld() == this, "Position's world %s doesn't equal chunk's world %s", pos.getWorld().getName(), this.getName());
        return getBlockAt(pos.getX(), pos.getY(), pos.getZ());
    }

    public Block getBlockAt(int x, int y, int z);
}
