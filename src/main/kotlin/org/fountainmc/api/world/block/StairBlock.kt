package org.fountainmc.api.world.block

import org.fountainmc.api.Direction

interface StairBlock : BlockState, DirectionalBlock {

    /**
     * Get the direction the stair is facing
     *
     * The direction is **never** vertical.

     * @return the direction
     */
    val direction: Direction

    /**
     * Get a clone of this stair's state facing the given direction
     *
     * The direction must **never be vertical!**

     * @param direction new direction
     * *
     * @return a stair state facing the given direction
     * *
     * @throws IllegalArgumentException if the direction is vertical
     */
    override fun withDirection(direction: Direction): StairBlock

    override fun mayBeVertical(): Boolean {
        return false
    }

    val isUpsideDown: Boolean

    fun withUpsideDown(b: Boolean): StairBlock

    /**
     * Get the type of the stairs

     * @return the type
     */
    val type: StairType

    fun asRightsideUp(): StairBlock {
        return withUpsideDown(false)
    }

    fun asUpsideDown(): StairBlock {
        return withUpsideDown(false)
    }

}
