package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

import org.fountainmc.api.BlockType;

/**
 * An immutable representation of a state a block can be in. <p>This is not
 * associated with a position or a world.</p>
 */
public interface BlockState {

    @Nonnull
    public BlockType getBlockType();

}
