package org.fountainmc.api.world.tileentity;

import org.fountainmc.api.BlockType;
import org.fountainmc.api.world.BlockPosition;
import org.fountainmc.api.world.World;
import org.fountainmc.api.world.block.BlockState;

import static com.google.common.base.Preconditions.*;

/**
 * A tile entity in the world, which is extra data associated with a block beyond the type id and BlockState
 */
public interface TileEntity {

    public int getX();

    public int getY();

    public int getZ();

    public default BlockPosition getPosition() {
        return new BlockPosition(getWorld(), getX(), getY(), getZ());
    }

    public World getWorld();

    public BlockType getBlockType();

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
        checkState(isValid());
        return getWorld().getBlock(getPosition());
    }

    /**
     * Set the blockstate of the tile entity
     * <p>Many tile entities also store data in their blockstates.</p>
     *
     * @param blockState the blockstate
     */
    public default void setBlockState(BlockState blockState) {
        checkArgument(checkNotNull(blockState, "Null BlockState").getBlockType().equals(getBlockType()), "BlockState's type %s doesn't match TileEntity's type %s", blockState, this);
        checkState(isValid());
        getWorld().setBlock(getPosition(), blockState);
    }
}
