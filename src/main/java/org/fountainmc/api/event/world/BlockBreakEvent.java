package org.fountainmc.api.event.world;

import org.fountainmc.api.event.Event;
import org.fountainmc.api.world.BlockPosition;

public class BlockBreakEvent extends Event {

    private BlockPosition block;

    public BlockBreakEvent(BlockPosition block) {
        this.block = block;
    }

    public BlockPosition getBlock() {
        return block;
    }

    public void setBlock(BlockPosition block) {
        this.block = block;
    }
}
