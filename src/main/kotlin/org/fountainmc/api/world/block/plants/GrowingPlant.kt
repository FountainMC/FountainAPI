package org.fountainmc.api.world.block.plants

import org.fountainmc.api.BlockType
import javax.annotation.Nonnegative

interface GrowingPlant : Plant {
    override val blockType: BlockType<GrowingPlant>

    val growthPercentage: Double
        get() = growth / maxGrowth.toDouble()

    // NOTE a utility wither for 'withGrowthPercentage' is left out here because
    // plugins should explicitly handle getMaxGrowth()

    val growth: Int

    /**
     * Get a copy of this plant with the given growth level
     *
     * @param growth the growth level
     * @return the new plant
     * @throws IllegalArgumentException if the growth level is negative or greater than [maxGrowth]
     */
    fun withGrowth(growth: Int) = copy(growth = growth)

    /**
     * Return a copy of this state with the specified values.
     */
    fun copy(growth: Int = this.growth): GrowingPlant

    /**
     * A utility method to get this type of plant's maximum growth height
     *
     * All block states of the same type must have the same maximum growth height.

     * @return the maximum growth height
     */
    @get:Nonnegative
    val maxGrowth: Int

    val isFresh: Boolean
        get() = growth == 0

}
