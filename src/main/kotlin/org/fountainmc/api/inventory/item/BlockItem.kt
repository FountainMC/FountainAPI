package org.fountainmc.api.inventory.item

import org.fountainmc.api.BlockType
import org.fountainmc.api.world.block.BlockState

interface BlockItem<out S: BlockState>: Item {
    val blockState: S
    override val type: BlockType<S>
}