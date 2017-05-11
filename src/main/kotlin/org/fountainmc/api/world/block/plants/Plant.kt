package org.fountainmc.api.world.block.plants

import org.fountainmc.api.BlockType
import org.fountainmc.api.world.block.BlockState

/**
 * Marker interface for a plant.
 */
interface Plant : BlockState {
    override val blockType: BlockType<Plant>
}
