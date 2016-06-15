package org.fountainmc.api;

import org.fountainmc.api.world.block.BlockState;

public interface BlockType extends Material {

    @Override
    public default boolean isBlock() {
        return true;
    }

    public boolean isFlammable();

    /**
     * Return if the block completely blocks all vision
     *
     * @return if opaque
     */
    public boolean isOpaque();

    /**
     * Return if the block is transparent and the player can see through it
     * <p>Transparent blocks do not block light.</p>
     *
     * @return if transparent
     */
    public boolean isTransparent();

    /**
     * Get the default state the block is in
     *
     * @return the default state of the block
     */
    public BlockState getDefaultState();

}
