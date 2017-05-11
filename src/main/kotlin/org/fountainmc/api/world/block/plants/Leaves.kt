package org.fountainmc.api.world.block.plants

interface Leaves : Plant {

    /**
     * Return if the leaves can decay

     * @return if able to decay
     */
    fun canDecay(): Boolean

    val type: LeafType

    enum class LeafType {
        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        ACACIA,
        DARK_OAK
    }

}
