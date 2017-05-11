package org.fountainmc.api.world.tileentity

import org.fountainmc.api.BlockType
import org.fountainmc.api.world.BlockPosition
import org.fountainmc.api.world.World
import org.fountainmc.api.world.block.BlockState
import kotlin.reflect.KClass
import kotlin.reflect.full.cast

/**
 * A tile entity in the world, which is extra data associated with a block beyond the type id and BlockState
 */
interface TileEntity<S: BlockState> {

    val x: Int

    val y: Int

    val z: Int

    val position: BlockPosition
        get() = BlockPosition(world, x, y, z)

    val world: World

    val blockType: BlockType<S>

    /**
     * Returns if the tile entity is still valid.
     *
     * Tile entities will be invalidated if the block changes.
     * Tile entities returned by [World.getTileEntity] should never be invalid at first,
     * but if you hold on to a reference for too long it might be.
     *
     * @return if valid
     */
    val isValid: Boolean

    /**
     * The blockstate of the tile entity
     *
     * Many tile entities also store data in their blockstates.
     */
    var blockState: S
        get() {
            check(isValid)
            return blockType.stateType.cast(world.getBlock(position))
        }
        set(blockState) {
            require(blockState.blockType == this.blockType) {
                "BlockState's type ${blockState.blockType} doesn't match TileEntity's type ${this.blockType}"
            }
            check(isValid)
            world.setBlock(position, blockState)
        }
}
