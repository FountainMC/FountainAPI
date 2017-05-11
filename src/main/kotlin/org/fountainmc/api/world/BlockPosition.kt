package org.fountainmc.api.world

import org.fountainmc.api.Direction
import org.fountainmc.api.entity.Entity
import org.fountainmc.api.entity.Player
import org.fountainmc.api.world.block.BlockState

data class BlockPosition(
        val world: World,
        val x: Int,
        val y: Int,
        val z: Int
) {
    /**
     * The x coordinate of the chunk this position is in.
     */
    inline val chunkX: Int
        get() = this.x shr 4
    /**
     * The y coordinate of the chunk this position is in.
     */
    inline val chunkZ: Int
        get() = this.y shr 4
    /**
     * The x coordinate of the block, relative to the chunk it lies in.
     */
    inline val relativeX: Int
        get() = this.x and 16
    /**
     * The y coordinate of the block, relative to the chunk it lies in.
     */
    inline val relativeZ: Int
        get() = this.y and 16

    fun requireChunk(chunk: Chunk) {
        requireWorld(chunk.world) {
            "Expected position to be in chunk's world $it: $this"
        }
        require(chunk.x == this.chunkX && chunk.z == this.chunkZ) {
            "Expected position to be in chunk (${chunk.x}, ${chunk.z}), not ($chunkX, $chunkZ): $this"
        }
    }

    fun requireWorld(player: Player) {
        requireWorld(player.world) {
            "Expected position to be in Player ${player.name}'s world $it: $this"
        }
    }

    fun requireWorld(entity: Entity) {
        requireWorld(entity.world) { "Expected position to be in Entity's world $it: $this" }
    }

    fun requireWorld(expected: World) {
        requireWorld(expected) { "Expected position to be in world $it: $this" }
    }

    inline fun requireWorld(expected: World, lazyMessage: BlockPosition.(World) -> String) {
        require(this.world == expected) { lazyMessage(this, expected) }
    }

    override fun toString() = "${world.name}($x, $y, $z)"

    @JvmOverloads
    fun offset(direction: Direction, amount: Int = 1): BlockPosition {
        var x = this.x
        var y = this.y
        var z = this.z
        when (direction) {
            Direction.NORTH -> x -= amount
            Direction.SOUTH -> x += amount
            Direction.EAST -> z += amount
            Direction.WEST -> z -= amount
            Direction.UP -> y += amount
            Direction.DOWN -> y -= amount
            else -> throw IllegalArgumentException("Invalid direction: " + direction)
        }
        return withCoordinates(x, y, z)
    }

    fun setState(state: BlockState) {
        world.setBlock(this, state)
    }

    fun withX(x: Int) = this.copy(x = x)

    fun withY(y: Int) = this.copy(y = y)

    fun withZ(z: Int) = this.copy(z = z)

    fun withCoordinates(x: Int, y: Int, z: Int) = this.copy(x = x, y = y, z = z)

    fun withWorld(world: World) = this.copy(world = world)

    fun toLocation() = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
}
