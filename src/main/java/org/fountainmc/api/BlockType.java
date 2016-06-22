package org.fountainmc.api;

import org.fountainmc.api.world.block.BlockState;

public interface BlockType extends Material {

    @Override
    default boolean isBlock() {
        return true;
    }

    boolean isFlammable();

    /**
     * Return if the block completely blocks all vision
     *
     * @return if opaque
     */
    boolean isOpaque();

    /**
     * Return if the block is transparent and the player can see through it
     * <p>Transparent blocks do not block light.</p>
     *
     * @return if transparent
     */
    boolean isTransparent();

    /**
     * Get the default state the block is in
     *
     * @return the default state of the block
     */
    BlockState getDefaultState();

}
