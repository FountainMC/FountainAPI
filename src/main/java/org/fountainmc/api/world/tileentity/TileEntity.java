package org.fountainmc.api.world.tileentity;

import org.fountainmc.api.BlockType;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.World;
import org.fountainmc.api.world.block.BlockState;

/**
 * A tile entity in the world, which is extra data associated with a block beyond the type id and BlockState
 */
public interface TileEntity {
    public BlockPosition getPosition();

    public World getWorld();

    public BlockType getType();

    /**
     * Returns if the tile entity is still valid.
     * <p>
     * Tile entities will be invalidated if the block changes.
     * Tile entities returned by {@link World#getTileEntity(int, int, int)} should never be invalid, but if you hold on to a reference for too long it might be.
     * </p>
     *
     * @return if valid
     */
    public boolean isValid();

    /**
     * Get the blockstate of the tile entity
     * <p>Many tile entities also store data in their blockstates.</p>
     *
     * @return the blockstate
     */
    public default BlockState getBlockState() {
        return getWorld().getBlock(getPosition());
    }
}
