package xyz.jadonfowler.fountain.api.world;

import xyz.jadonfowler.fountain.api.world.block.Block;

public interface World {

    Block getBlock(int x, int y, int z);
}
