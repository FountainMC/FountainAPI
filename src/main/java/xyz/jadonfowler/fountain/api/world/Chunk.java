package xyz.jadonfowler.fountain.api.world;

import xyz.jadonfowler.fountain.api.world.block.Block;

public interface Chunk {

    int getX();

    int getZ();

    World getWorld();

    Block getBlockAt(int x, int y, int z);
}
