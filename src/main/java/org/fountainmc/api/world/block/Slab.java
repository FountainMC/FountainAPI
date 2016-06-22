package org.fountainmc.api.world.block;

import javax.annotation.Nonnull;

@Nonnull
public interface Slab extends BlockState {

    /**
     * Get the block type of the slab
     *
     * @return the type of the slab
     */
    StairType getType();

    /**
     * Return a copy of this slab with the given slab typez
     *
     * @param type the new type
     * @return the new slab
     */
    Slab withType(StairType type);

    /**
     * If the slab is upside-down, and occupies the top half of its tile.
     *
     * @return if upside-down
     */
    boolean isUpsideDown();

    /**
     * If the slab is rightside-up, and occupies the bottom half of its tile.
     *
     * @return if rightside-up
     */
    boolean isRightsideUp();

    StairBlock withUpsideDown(boolean b);

    default StairBlock asRightsideUp() {
        return withUpsideDown(false);
    }

    default StairBlock asUpsideDown() {
        return withUpsideDown(false);
    }

}
