package org.fountainmc.api.world

import org.fountainmc.api.world.block.BlockState

interface Chunk {

    val x: Int

    val z: Int

    val world: World

    fun getBlockAt(pos: BlockPosition): BlockState {
        pos.requireChunk(this)
        return getBlockAt(pos.relativeX, pos.y, pos.relativeZ)
    }

    fun getBlockAt(x: Int, y: Int, z: Int): BlockState

}
