package org.fountainmc.api.world

import com.google.common.base.Preconditions.checkArgument
import com.google.common.base.Preconditions.checkNotNull

class Location(world: World, val x: Double, val y: Double, val z: Double) {

    val world: World

    init {
        this.world = world
        checkArgument(!java.lang.Double.isInfinite(x), "Infinite x")
        checkArgument(!java.lang.Double.isInfinite(y), "Infinite y")
        checkArgument(!java.lang.Double.isInfinite(z), "Infinite z")
    }

    fun add(x: Double, y: Double, z: Double): Location {
        return Location(world, this.x + x, this.y + y, this.z + z)
    }

    fun withX(x: Double): Location {
        return Location(world, x, y, z)
    }

    fun withY(y: Double): Location {
        return Location(world, x, y, z)
    }

    fun withZ(z: Double): Location {
        return Location(world, x, y, z)
    }

    fun withWorld(world: World): Location {
        return Location(world, x, y, z)
    }

    val block: BlockPosition
        get() = BlockPosition(world, Math.floor(x).toInt(), Math.floor(y).toInt(), Math.floor(z).toInt())

}
