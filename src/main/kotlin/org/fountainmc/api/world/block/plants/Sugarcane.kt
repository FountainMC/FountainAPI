package org.fountainmc.api.world.block.plants

import org.fountainmc.api.BlockType
import javax.annotation.Nonnegative

interface Sugarcane : GrowingPlant {
    override val blockType: BlockType<Sugarcane>

    override fun withGrowth(@Nonnegative growth: Int): Sugarcane

    override val maxGrowth: Int
        get() = MAXIMUM_GROWTH

    companion object {

        val MAXIMUM_GROWTH = 16
    }

}
