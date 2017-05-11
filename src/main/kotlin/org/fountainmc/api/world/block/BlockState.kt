package org.fountainmc.api.world.block

import org.fountainmc.api.BlockType

/**
 * An immutable representation of a state a block can be in.
 *
 * This is not associated with a position or a world.
 */
interface BlockState {
    val blockType: BlockType<*>
}
