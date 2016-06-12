package org.fountainmc.api.world;

import org.fountainmc.api.world.block.BlockState;

public interface World {

    String getName();

    Chunk getChunk(int x, int y);

    BlockState getBlockAt(int x, int y, int z);
}
