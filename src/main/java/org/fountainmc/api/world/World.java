package org.fountainmc.api.world;

public interface World {

    String getName();

    Chunk getChunk(int x, int y);

    BlockPosition getBlockAt(int x, int y, int z);
}
