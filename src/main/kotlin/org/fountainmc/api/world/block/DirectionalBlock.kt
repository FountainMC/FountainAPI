package org.fountainmc.api.world.block

import org.fountainmc.api.Direction

interface DirectionalBlock : BlockState {

    fun withDirection(direction: Direction): DirectionalBlock

    fun mayBeVertical(): Boolean

}
