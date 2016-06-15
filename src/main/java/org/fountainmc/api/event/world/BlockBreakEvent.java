package org.fountainmc.api.event.world;

import org.fountainmc.api.event.CancellableBase;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.block.BlockState;

public class BlockBreakEvent extends CancellableBase {

    private final BlockState state;
    private final BlockPosition position;

    public BlockBreakEvent(BlockState state, BlockPosition position) {
        this.state = state;
        this.position = position;
    }

    public BlockPosition getPosition() {
        return position;
    }

    public BlockState getState() {
        return state;
    }

}
