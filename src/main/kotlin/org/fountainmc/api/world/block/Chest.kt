package org.fountainmc.api.world.block

import org.fountainmc.api.Direction

interface Chest : BlockState, DirectionalBlock {

    val direction: Direction

    /**
     * Get a clone of this chest with the given direction

     * @param direction the new direction
     * *
     * @return the new chest
     */
    override fun withDirection(direction: Direction): Chest

    override fun mayBeVertical(): Boolean {
        return false
    }

}
