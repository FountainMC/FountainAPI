package org.fountainmc.api.world.block

interface LiquidBlock : BlockState {

    val level: Int

    val isFlowing: Boolean

    /**
     * Return a copy of this block with the given values.
     */
    fun copy(level: Int = this.level, isFlowing: Boolean = this.isFlowing)
}
