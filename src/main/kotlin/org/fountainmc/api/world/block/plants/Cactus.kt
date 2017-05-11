package org.fountainmc.api.world.block.plants

import org.fountainmc.api.BlockType

interface Cactus : GrowingPlant {
    override val blockType: BlockType<Cactus>

    override fun withGrowth(growth: Int): Cactus

    override val maxGrowth: Int
        get() = MAXIMUM_GROWTH

    companion object {
        val MAXIMUM_GROWTH = 16
    }

}
