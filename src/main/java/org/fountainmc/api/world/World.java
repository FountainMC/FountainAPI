package org.fountainmc.api.world;

import org.fountainmc.api.world.block.BlockState;

public interface World {

    String getName();

    Chunk getChunk(int x, int y);

    BlockState getBlock(int x, int y, int z);

    default BlockState getBlock(BlockPosition pos) {
        return getBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    void setBlock(int x, int y, int z, BlockState state);

    default void setBlock(BlockPosition pos, BlockState state) {
        setBlock(pos.getX(), pos.getY(), pos.getZ(), state);
    }

}
