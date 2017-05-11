package org.fountainmc.api.world.block

interface DoubleSlab : BlockState {

    /**
     * Get the block type of the slab

     * @return the type of the slab
     */
    val type: StairType

    /**
     * Get a copy of this slab with the given type

     * @param type the new type
     * *
     * @return the new slab
     */
    fun withType(type: StairType): Slab

    val isSmooth: Boolean

    fun withSmoothness(smooth: Boolean): DoubleSlab

    fun asSmooth(): DoubleSlab {
        return withSmoothness(true)
    }

    fun asRough(): DoubleSlab {
        return withSmoothness(false)
    }

}
