package org.fountainmc.api.world.block.plants

/**
 * Data for a 'grass' block, which may or may not actually be grass. Note that
 * double tall grass resides in [DoublePlant]
 */
interface Grass : Plant {

    val type: GrassType

    enum class GrassType {
        SHRUB,
        TALL_GRASS,
        FERN
    }

}
