package org.fountainmc.api.world.block

interface Slab : BlockState {

    /**
     * Get the block type of the slab

     * @return the type of the slab
     */
    val type: StairType

    /**
     * Return a copy of this slab with the given slab typez

     * @param type the new type
     * *
     * @return the new slab
     */
    fun withType(type: StairType): Slab

    /**
     * If the slab is upside-down, and occupies the top half of its tile.

     * @return if upside-down
     */
    val isUpsideDown: Boolean

    /**
     * If the slab is rightside-up, and occupies the bottom half of its tile.

     * @return if rightside-up
     */
    val isRightsideUp: Boolean

    fun withUpsideDown(b: Boolean): StairBlock

    fun asRightsideUp(): StairBlock {
        return withUpsideDown(false)
    }

    fun asUpsideDown(): StairBlock {
        return withUpsideDown(false)
    }

}
