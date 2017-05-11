package org.fountainmc.api

import org.fountainmc.api.inventory.item.BlockItem
import org.fountainmc.api.world.block.BlockState
import kotlin.reflect.KClass

interface BlockType<out S: BlockState> : Material<BlockItem<S>> {

    val stateType: KClass<@UnsafeVariance S>

    override val isBlock
        get() = true

    /**
     * If the block is flammable.
     */
    val isFlammable: Boolean

    /**
     * If the block completely blocks all vision
     */
    val isOpaque: Boolean

    /**
     * If the block is transparent and the player can see through it
     *
     * Transparent blocks do not block light.
     */
    val isTransparent: Boolean

    /**
     * The default state the block is in.
     */
    val defaultState: S
}
