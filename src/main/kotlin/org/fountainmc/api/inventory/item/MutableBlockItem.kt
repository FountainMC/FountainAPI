package org.fountainmc.api.inventory.item

import org.fountainmc.api.world.block.BlockState

interface MutableBlockItem<S: BlockState>: BlockItem<S>, MutableItem {
    override var blockState: S
}