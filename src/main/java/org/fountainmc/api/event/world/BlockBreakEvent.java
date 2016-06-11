package org.fountainmc.api.event.world;

import org.fountainmc.api.event.CancellableBase;
import org.fountainmc.api.world.BlockPosition;

public class BlockBreakEvent extends CancellableBase {

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
