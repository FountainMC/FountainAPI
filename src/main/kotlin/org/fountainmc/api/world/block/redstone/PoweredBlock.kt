package org.fountainmc.api.world.block.redstone

import javax.annotation.Nonnegative

import org.fountainmc.api.world.block.BlockState

interface PoweredBlock : BlockState {

    @get:Nonnegative
    val power: Int

    /**
     * Get a clone of this block with the given power
     *
     * @param power the new power
     * @return the new block state
     * @throws IllegalArgumentException if power is negative or greater than [MAXIMUM_POWER]
     */
    fun withPower(@Nonnegative power: Int): PoweredBlock

    companion object {
        /**
         * The maximum power a [PoweredBlock] can have.
         */
        const val MAXIMUM_POWER = 15
    }

}
