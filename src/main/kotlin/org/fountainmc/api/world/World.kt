package org.fountainmc.api.world

import org.fountainmc.api.Server
import org.fountainmc.api.world.block.BlockState
import org.fountainmc.api.world.tileentity.TileEntity

interface World {

    val server: Server

    val name: String

    fun getChunk(x: Int, y: Int): Chunk

    fun getBlock(x: Int, y: Int, z: Int): BlockState

    fun getBlock(pos: BlockPosition): BlockState {
        pos.requireWorld(this)
        return getBlock(pos.x, pos.y, pos.z)
    }

    fun getTileEntity(x: Int, y: Int, z: Int): TileEntity<*>

    fun getTileEntity(pos: BlockPosition): TileEntity<*> {
        pos.requireWorld(this)
        return getTileEntity(pos.x, pos.y, pos.z)
    }

    fun setBlock(x: Int, y: Int, z: Int, state: BlockState)

    fun setBlock(pos: BlockPosition, state: BlockState) {
        setBlock(pos.x, pos.y, pos.z, state)
    }
}
